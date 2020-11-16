package com.codecamp.laokycoidc

import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.codecamp.laokycmodule.dtos.OTPRequest
import com.codecamp.laokycmodule.services.IOTPService
import com.codecamp.laokycmodule.services.IRegisterConfigService
import com.codecamp.laokycmodule.services.ISingleSignOn
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    // 1.
    private val registerConfig : IRegisterConfigService by inject()
    private val requestOTP : IOTPService by inject()
    private val singleSignOn : ISingleSignOn by inject()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 2. Register config
        //registerConfig.RegisterConfigBuilder(this@LoginActivity , R.raw.oidc)

        val result = """
        {
           // Contact LaoKYC Team
        }        
        """

        val jsonObject = JSONObject(result)
        registerConfig.RegisterConfigBuilder(this@LoginActivity , jsonObject)
        // API Check Auth State
        /*singleSignOn.CheckAuthState(CheckAuthStateRequest("432425235235" , "https://gateway.sbg.la/" , this@LoginActivity)){ result ->
           // tvText.text = result.Code.toString()
        }*/


        btnSignInWithLaoKYC.setOnClickListener() {

            // Init OTP Service
            var device = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
            var otpRequest = OTPRequest( "" , device  , "https://gateway.sbg.la/api/login"  ,
                this@LoginActivity)

            requestOTP.showDialog( otpRequest  , MainActivity::class.java)

        }



    }
}