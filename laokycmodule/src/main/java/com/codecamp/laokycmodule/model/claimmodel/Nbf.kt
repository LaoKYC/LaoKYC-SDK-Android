package com.gov.mpt.laokyc.model.claimmodel


import com.google.gson.annotations.SerializedName

data class Nbf(
    @SerializedName("value")
    val value: Int?
)