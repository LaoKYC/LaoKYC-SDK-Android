package com.codecamp.laokycoidc

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codecamp.laokycmodule.dtos.CheckAuthStateResponse
import com.codecamp.laokycmodule.dtos.OIDCRequest
import com.codecamp.laokycmodule.dtos.OTPRequest
import com.codecamp.laokycmodule.services.*
import com.codecamp.laokycoidc.*
import kotlinx.android.synthetic.main.activity_main.*
import net.openid.appauth.BuildConfig
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val claimService : IClaimService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText.text = claimService.firstName + " " + claimService.familyName + " "+ claimService.preferredUsername



    }

}
