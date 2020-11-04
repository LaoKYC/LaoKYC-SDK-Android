package com.codecamp.laokycmodule.oauth;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.codecamp.laokycmodule.BuildConfig;
import com.codecamp.laokycmodule.services.IOIDCConfig;

import net.openid.appauth.AppAuthConfiguration;
import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.TokenResponse;

public class AuthManager {
	private static AuthManager instance;
	private AuthState mAuthState;
	private Auth mAuth;
	private AuthorizationServiceConfiguration mAuthConfig;
	private SharedPreferencesRepository mSharedPrefRep;
	private AuthorizationService mAuthService;

	private IOIDCConfig configs ;

	public static AuthManager getInstance(Context context , IOIDCConfig ioidcConfig) {
		if (instance == null) {
			instance = new AuthManager(context , ioidcConfig);
		}
		return instance;
	}

	private AuthManager(Context context , IOIDCConfig ioidcConfig){

		configs = ioidcConfig ;

		mSharedPrefRep = new SharedPreferencesRepository(context);
		setAuthData();

		mAuthConfig = new AuthorizationServiceConfiguration(
				Uri.parse(ioidcConfig.getAUTHORIZSTION_END_POINT_URI()),
				Uri.parse(ioidcConfig.getTOKEN_END_POINT_URI()),
				null);
		mAuthState = mSharedPrefRep.getAuthState();

		AppAuthConfiguration.Builder appAuthConfigBuilder = new AppAuthConfiguration.Builder();

		//To Allow Http in requests in debug mode
		if(BuildConfig.DEBUG)
			appAuthConfigBuilder.setConnectionBuilder(AppAuthConnectionBuilderForTesting.INSTANCE);

		AppAuthConfiguration appAuthConfig = appAuthConfigBuilder.build();
		mAuthService = new AuthorizationService(context, appAuthConfig);
	}



	public AuthorizationServiceConfiguration getAuthConfig() {
		return mAuthConfig;
	}

	public Auth getAuth() {
		if(mAuth == null){
           setAuthData();
        }
        return mAuth;
	}

	public AuthState getAuthState(){
		return mAuthState;
	}

	public void updateAuthState(TokenResponse response, AuthorizationException ex){
		mAuthState.update(response,ex);
		mSharedPrefRep.saveAuthState(mAuthState);
	}

	public void clearAuthState(TokenResponse response, AuthorizationException ex){
		mAuthState.update(response,ex);
		mSharedPrefRep.saveAuthState(mAuthState);
	}

	public void setAuthState(AuthorizationResponse response, AuthorizationException ex){
		if(mAuthState == null)
			mAuthState = new AuthState(response,ex);

        mSharedPrefRep.saveAuthState(mAuthState);
	}

	public AuthorizationService getAuthService(){
		return mAuthService;
	}

	private void setAuthData(){


        mAuth = new Auth();
        //val _ClientID = com.codecamp.laokycoidc.BuildConfig.CLIENT_ID;
        mAuth.setClientId(configs.getClientID());
        mAuth.setAuthorizationEndpointUri(configs.getAUTHORIZSTION_END_POINT_URI());
        mAuth.setClientSecret(configs.getClientSecret());
        mAuth.setRedirectUri(configs.getREDIRECT_URI());
        mAuth.setScope(configs.getSCOPE());
        mAuth.setTokenEndpointUri(configs.getTOKEN_END_POINT_URI());
        mAuth.setResponseType(configs.getRESPONSE_TYPE());

    }

	/*@AnyThread
	@NonNull
	public AuthState replace(@NonNull AuthState state) {
		writeState(state);
		mCurrentAuthState.set(state);
		return state;
	}

	@AnyThread
	private void writeState(@Nullable AuthState state) {
		mPrefsLock.lock();
		try {
			SharedPreferences.Editor editor = mPrefs.edit();
			if (state == null) {
				editor.remove(KEY_STATE);
			} else {
				editor.putString(KEY_STATE, state.jsonSerializeString());
			}

			if (!editor.commit()) {
				throw new IllegalStateException("Failed to write state to shared prefs");
			}
		} finally {
			mPrefsLock.unlock();
		}
	}*/
}
