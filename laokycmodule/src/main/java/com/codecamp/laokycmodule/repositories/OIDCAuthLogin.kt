package com.codecamp.laokycmodule.repositories

import android.R
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.codecamp.laokycmodule.dtos.OIDCRequest
import com.codecamp.laokycmodule.dtos.OTPResponse
import com.codecamp.laokycmodule.oauth.Auth
import com.codecamp.laokycmodule.oauth.AuthManager
import com.codecamp.laokycmodule.oauth.SharedPreferencesRepository
import com.codecamp.laokycmodule.services.IOIDCConfig
import com.codecamp.laokycmodule.services.IOIDCService
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.CodeVerifierUtil

import org.koin.java.KoinJavaComponent.inject


class OIDCAuthLogin(var config : IOIDCConfig) : IOIDCService  {

   // private val oidcConfig : IOIDCConfig()

    override fun OIDCAuthLogin(Request: OIDCRequest) {

        val authManager: AuthManager = AuthManager.getInstance(Request.activity , config )
        val authService: AuthorizationService = authManager.getAuthService()
        // val auth: Auth = authManager.getAuth()

        val additionalParams: MutableMap<String, String> =
            HashMap()
        additionalParams["phone"] = Request.phone
        additionalParams["platform"] = Request.platform

        val authRequestBuilder = AuthorizationRequest.Builder(
            authManager.getAuthConfig(),
            config.ClientID,
            config.RESPONSE_TYPE,
            Uri.parse(config.REDIRECT_URI)
        )
            .setScope(config.SCOPE)
            .setAdditionalParameters(additionalParams)
            .setPrompt(Request.prompt)



        //Generate and save code verifier to be used later
        val codeVerifier = CodeVerifierUtil.generateRandomCodeVerifier()
        val sharedPreferencesRepository =
            SharedPreferencesRepository(Request.activity)
        sharedPreferencesRepository.saveCodeVerifier(codeVerifier)

        authRequestBuilder.setCodeVerifier(codeVerifier)

        val authRequest = authRequestBuilder.build()



        val authIntent = Intent(Request.activity, Request.redirectActivity)
        val pendingIntent = PendingIntent.getActivity(Request.activity, authRequest.hashCode(), authIntent, 0)

       /* authService.performAuthorizationRequest(
            authRequest,
            pendingIntent
        )*/


        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(
            ContextCompat.getColor(Request.activity, R.color.holo_orange_light)
        )

      /*  builder.setToolbarColor(
            ContextCompat.getColor(Request.activity, R.color.transparent)
        )*/
        builder.setShowTitle(true)
        builder.addDefaultShareMenuItem()
        builder.setStartAnimations(Request.activity, android.R.anim.fade_in, android.R.anim.fade_out)
        builder.setExitAnimations(Request.activity, android.R.anim.fade_in, android.R.anim.fade_out)
        //builder.setCloseButtonIcon(bitmap)
       // builder.setActionButton(bitmap, "Android", pendingIntent, true)
        builder.enableUrlBarHiding()
        authService.performAuthorizationRequest(
            authRequest,
            pendingIntent,
            builder.build()
        )


    }

    override fun OIDCCallback(redirectActivity: Activity, Callback: (OTPResponse) -> Unit) {

    }



}