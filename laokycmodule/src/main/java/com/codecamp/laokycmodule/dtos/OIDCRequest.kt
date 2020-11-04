package com.codecamp.laokycmodule.dtos

import android.app.Activity

data class OIDCRequest(
   var activity: Activity,
   var phone: String,
   var platform: String,
   var prompt: String,
   var redirectActivity: Class<*>


)