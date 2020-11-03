package com.codecamp.laokycmodule.dtos

import android.app.Activity

data class CheckAuthStateRequest (
   var AccessToken : String,
   var URL_API : String ,
   var activity : Activity
)