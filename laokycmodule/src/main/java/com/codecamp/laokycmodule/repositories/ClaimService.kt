package com.codecamp.laokycmodule.repositories

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.auth0.android.jwt.JWT
import com.codecamp.laokycmodule.model.ModelClaims
import com.codecamp.laokycmodule.oauth.AuthManager
import com.codecamp.laokycmodule.oauth.SharedPreferencesRepository
import com.codecamp.laokycmodule.services.IClaimService
import com.codecamp.laokycmodule.services.IOIDCConfig
import com.google.gson.Gson
import net.openid.appauth.*

class ClaimService(var oidcConfig: IOIDCConfig) : IClaimService {

    private var mAuthService: AuthorizationService? = null


    override var accessToken: String = ""
    override var firstName: String = ""
    override var familyName: String = ""
    override var phoneNumber: String = ""
    override var picture: String = ""
    override var userID: String = ""
    override var preferredUsername: String = ""
    override var gender: String = ""

    override fun ExtractClaims(context: Context, intent: Intent) {

        val resp = AuthorizationResponse.fromIntent(intent)
        val ex =
            AuthorizationException.fromIntent(intent)

        val authManager = AuthManager.getInstance(context , oidcConfig)
        try {
            authManager.setAuthState(resp, ex)
        } catch (e:Exception) {

        }



        if (resp != null) {
            val clientSecretPost =
                ClientSecretPost(authManager.auth.clientSecret)
            val tokenRequest = TokenRequest.Builder(
                authManager.authConfig,
                authManager.auth.clientId
            )
                .setAuthorizationCode(resp.authorizationCode)
                .setRedirectUri(Uri.parse(authManager.auth.redirectUri))
                .setCodeVerifier(SharedPreferencesRepository(context).codeVerifier)
                .build()
            mAuthService = authManager.authService

            mAuthService!!.performTokenRequest(
                tokenRequest,
                clientSecretPost,
                AuthorizationService.TokenResponseCallback { response, ex ->

                    if (ex == null) {
                        authManager.updateAuthState(response, ex)
                        // MyApp.Token = authManager.authState.idToken
                        accessToken = authManager.authState.accessToken.toString()

                        val jwt = JWT(authManager.authState.idToken!!)
                        val allClaims =
                            jwt.claims
                        val gson = Gson()
                        val _allClaims = gson.toJson(allClaims)
                        val _result = gson.fromJson(_allClaims, ModelClaims::class.java)

                        phoneNumber = _result!!.phone!!.value.toString()
                        firstName = _result!!.name!!.value.toString()
                        familyName = _result!!.familyName!!.value.toString()
                        preferredUsername = _result!!.preferredUsername!!.value.toString()
                        userID = _result!!.sub!!.value.toString()
                       // gender = _result!!.gender!!.toString()
                        picture = "https://gateway.sbg.la/api/render/MyPhoto/" + preferredUsername + "?"


                    }
                })
            // authorization completed
        } else {
            // authorization failed, check ex for more details
            /*val loginIntent = Intent(this@LoginOnResultActivity, LoginActivity::class.java)
            startActivity(loginIntent)*/

        }
    }

}