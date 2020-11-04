package com.codecamp.laokycmodule.dtos

data class OTPResponse (
    val Code: Int,
    val Message: String,
    val IsSuccess: Boolean
)
