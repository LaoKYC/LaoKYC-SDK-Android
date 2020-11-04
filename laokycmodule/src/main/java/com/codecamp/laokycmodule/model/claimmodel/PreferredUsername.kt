package com.gov.mpt.laokyc.model.claimmodel


import com.google.gson.annotations.SerializedName

data class PreferredUsername(
    @SerializedName("value")
    val value: String?
)