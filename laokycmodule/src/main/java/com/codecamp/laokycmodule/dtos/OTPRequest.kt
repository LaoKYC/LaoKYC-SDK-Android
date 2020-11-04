package com.codecamp.laokycmodule.dtos

import android.app.Activity

data class OTPRequest(
   var Phone: String,
   var Device: String,
   var URL_API: String,
   var activity: Activity
)