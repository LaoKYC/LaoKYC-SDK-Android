package com.codecamp.laokycoidc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codecamp.laokycmodule.services.*
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val claimService : IClaimService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Register Claim
        claimService.ExtractClaims( this@MainActivity , intent)

       // tvText.text = claimService.firstName + " " + claimService.familyName + " "+ claimService.preferredUsername + "\n" + claimService.accessToken
        tvCovidDashboardPhoneNumber.text = claimService.preferredUsername
        tvCovidFirstNameAndSurName.text = claimService.firstName + " " + claimService.familyName

        Picasso.get().load("https://gateway.sbg.la/api/render/MyPhoto/" + claimService.preferredUsername + "?")
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .resize(512, 670).into(ivCovidDashboardPhotoProfile)

        tvCovidAccessToken.text = claimService.accessToken

        if (claimService.preferredUsername == "") {
            this.recreate()
        }


    }

}
