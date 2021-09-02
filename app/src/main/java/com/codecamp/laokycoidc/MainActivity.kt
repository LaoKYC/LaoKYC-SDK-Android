package com.codecamp.laokycoidc

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.codecamp.laokycmodule.services.*
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val claimService : IClaimService by inject()
    //var _loop : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Register Claim
        claimService.ExtractClaims(this@MainActivity, intent)

        if (claimService.isLogOut == true) {
            //this.recreate()
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        tvCovidDashboardPhoneNumber.text = claimService.preferredUsername


        tvCovidFirstNameAndSurName.text = claimService.firstName + " " + claimService.familyName

        Picasso.get().load("https://gateway.sbg.la/api/render/MyPhoto/" + claimService.preferredUsername + "?")
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .resize(512, 670).into(ivCovidDashboardPhotoProfile)

        tvCovidAccessToken.text = claimService.accessToken
        val _sub = claimService.sub
        val _account = claimService.account
        val _factor = claimService.factor



        /*val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            "LogOut",
            Context.MODE_PRIVATE
        )
        var _flg = sharedPreferences.getString("flg", "")

        if (_flg.equals("logout") ) {
            this.recreate()
            saveLogOut("login" , this)
        } else {

        }*/


       /* else {
            for ( i in 0..1) {
                if (i ==0 ) {
                    //if (claimService.preferredUsername == "") {
                        this.recreate()
                    //}
                    //saveLogOut("login" , this)
                } else {
                    saveLogOut("logout" , this)
                }
            }
        }*/

       /* val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            "LogOut",
            Context.MODE_PRIVATE
        )
        var _flg = sharedPreferences.getString("flg", "")
        if (_flg.equals("login")) {

           // this.recreate()
           // saveLogOut("login" , this)

        } else {
            //this.recreate()
            for ( i in 0..1) {
                if (i !=1) {
                    this.recreate()
                    saveLogOut("login" , this)
                } else {
                    //saveLogOut("logout" , this)
                }


            }
        }*/



        // Factor | IDToken

        btnLogiOut.setOnClickListener({

            claimService.isLogOut = true
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        })


        
    }

    fun saveLogOut(flg: String, context: Context) {
        val sharedPreference =  context.getSharedPreferences("LogOut", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("flg", flg)
        editor.commit()
    }


}
