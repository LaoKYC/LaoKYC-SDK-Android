package com.codecamp.laokycmodule.services

import com.codecamp.laokycmodule.dtos.CheckAuthStateResponse

interface ISingleSignOn {
    fun CheckAuthState() : CheckAuthStateResponse
}