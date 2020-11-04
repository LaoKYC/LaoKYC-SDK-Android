package com.codecamp.laokycmodule.services

import android.app.Activity
import com.codecamp.laokycmodule.dtos.OIDCRequest
import com.codecamp.laokycmodule.dtos.OTPRequest
import com.codecamp.laokycmodule.dtos.OTPResponse

interface IOTPService {
    fun RequestOTP(Request: OTPRequest, Callback: (OTPResponse) -> Unit)

    fun showDialog(request : OTPRequest, redirectActivity : Class<*>)

}