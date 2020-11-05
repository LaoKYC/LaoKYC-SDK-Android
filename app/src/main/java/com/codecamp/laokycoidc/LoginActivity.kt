package com.codecamp.laokycoidc

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codecamp.laokycmodule.dtos.CheckAuthStateRequest
import com.codecamp.laokycmodule.dtos.OIDCRequest
import com.codecamp.laokycmodule.dtos.OTPRequest
import com.codecamp.laokycmodule.services.IOIDCService
import com.codecamp.laokycmodule.services.IOTPService
import com.codecamp.laokycmodule.services.IRegisterConfigService
import com.codecamp.laokycmodule.services.ISingleSignOn
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    // 1.
    private val authLogin : IOIDCService by inject()

    // 2.
    private val registerConfig : IRegisterConfigService by inject()

    private val requestOTP : IOTPService by inject()

    private val singleSignOn : ISingleSignOn by inject()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 3. Register config
        registerConfig.RegisterConfigBuilder(this@LoginActivity , R.raw.oidc)

        // API Check Auth State
        singleSignOn.CheckAuthState(CheckAuthStateRequest("432425235235" , "https://gateway.sbg.la/" , this@LoginActivity)){ result ->
           // tvText.text = result.Code.toString()
        }

        btnSignInWithLaoKYC.setOnClickListener() {

            // Init OTP Service
            var Device = "Tatum's Device"
            var otpRequest = OTPRequest( "" ,Device  , "https://gateway.sbg.la/api/login"  ,this@LoginActivity)

            requestOTP.showDialog( otpRequest  , MainActivity::class.java)

        }



    }
}