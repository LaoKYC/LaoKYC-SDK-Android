package com.codecamp.laokycoidc

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.codecamp.laokycmodule.dtos.CheckAuthStateRequest
import com.codecamp.laokycmodule.dtos.CheckAuthStateResponse
import com.codecamp.laokycmodule.repositories.SingleSignOn
import com.codecamp.laokycmodule.services.ISingleSignOn
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val singleSignOn : ISingleSignOn by inject()
    var result : CheckAuthStateResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        singleSignOn.CheckAuthState(CheckAuthStateRequest("432425235235" , "https://api.oneid.sbg.la/" , this@MainActivity)){ result ->
            tvText.text = result.Code.toString()
        }


        //Toast.makeText(this , result.Code , Toast.LENGTH_LONG).show()
        // val result = SingleSignOn().CheckAuthState("wewerwer" , "https://gateway.sbg.la/" , this@MainActivity)
    }

}
