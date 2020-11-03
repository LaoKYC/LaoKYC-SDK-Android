package com.codecamp.laokycmodule.services

import android.app.Activity
import com.codecamp.laokycmodule.dtos.CheckAuthStateRequest
import com.codecamp.laokycmodule.dtos.CheckAuthStateResponse

interface ISingleSignOn {
    fun CheckAuthState(Request: CheckAuthStateRequest, Callback: (CheckAuthStateResponse) -> Unit)
}