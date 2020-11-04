package com.codecamp.laokycoidc


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.codecamp.laokycmodule.services.IClaimService
import com.codecamp.laokycmodule.services.IOIDCConfig
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import org.koin.android.ext.android.inject


/**
 * Created by SBlab on 2020-10-29.
 */

class LoginOnResultActivity : AppCompatActivity() {

    private val claimService : IClaimService by inject()

    private var btnResultData : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)

        btnResultData = findViewById(R.id.btnResultData)
        // Register Claim
        claimService.ExtractClaims( this@LoginOnResultActivity , intent)

        /*val mainIntent = Intent(this@LoginOnResultActivity, MainActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(mainIntent)*/

        btnResultData!!.setOnClickListener {
            val mainIntent = Intent(this@LoginOnResultActivity, MainActivity::class.java)
            mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mainIntent)
        }


    }


}
