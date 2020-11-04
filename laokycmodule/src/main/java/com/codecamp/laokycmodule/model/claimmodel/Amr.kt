package com.gov.mpt.laokyc.model.claimmodel


import com.google.gson.annotations.SerializedName

data class Amr(
    @SerializedName("value")
    val value: List<String?>?
)