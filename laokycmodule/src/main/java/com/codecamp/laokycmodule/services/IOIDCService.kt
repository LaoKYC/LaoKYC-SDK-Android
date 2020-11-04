package com.codecamp.laokycmodule.services

import android.app.Activity
import android.content.Intent
import com.codecamp.laokycmodule.dtos.OIDCRequest
import com.codecamp.laokycmodule.dtos.OTPResponse
import com.codecamp.laokycmodule.repositories.OIDCAuthLogin

interface IOIDCService {
    fun OIDCAuthLogin(Request: OIDCRequest )

    fun OIDCCallback(redirectActivity : Activity , Callback: (OTPResponse) -> Unit)

}