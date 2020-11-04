package com.gov.mpt.laokyc.model.claimmodel


import com.google.gson.annotations.SerializedName

data class Iat(
    @SerializedName("value")
    val value: Int?
)