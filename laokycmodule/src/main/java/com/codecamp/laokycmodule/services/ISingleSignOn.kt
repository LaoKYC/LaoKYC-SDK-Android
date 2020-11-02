package com.codecamp.laokycmodule.services

import android.app.Activity
import com.codecamp.laokycmodule.dtos.CheckAuthStateResponse

interface ISingleSignOn {
    fun CheckAuthState( AccessToken : String , URL_API : String , activity: Activity) : CheckAuthStateResponse
}