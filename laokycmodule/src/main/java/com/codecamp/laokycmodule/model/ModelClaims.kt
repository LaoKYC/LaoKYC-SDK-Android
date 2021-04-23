package com.codecamp.laokycmodule.model

import com.google.gson.annotations.SerializedName
import com.gov.mpt.laokyc.model.claimmodel.*

data class ModelClaims(
        /*@SerializedName("amr")
        val amr: Amr?,
        @SerializedName("at_hash")
        val atHash: AtHash?,
        @SerializedName("aud")
        val aud: Aud?,
        @SerializedName("auth_time")
        val authTime: AuthTime?,
        @SerializedName("exp")
        val exp: Exp?,

        @SerializedName("iat")
        val iat: Iat?,
        @SerializedName("idp")
        val idp: Idp?,
        @SerializedName("iss")
        val iss: Iss?,*/
        @SerializedName("name")
        val name: Name?,
        /*@SerializedName("nbf")
        val nbf: Nbf?,*/
        @SerializedName("phone")
        val phone: Phone?,
        @SerializedName("picture")
        val picture: Picture?,
        @SerializedName("sub")
        val sub: Sub?,
        @SerializedName("family_name")
        val familyName: FamilyName?,
        @SerializedName("preferred_username")
        val preferredUsername: PreferredUsername?,
        @SerializedName("gender")
        val gender: Gender?,
        @SerializedName("account")
        val account: Account?

       /*
        @SerializedName("s_hash")
        val sHash: SHash?,
        @SerializedName("sid")
        val sid: Sid?,

        @SerializedName("website")
        val website: Website?,
        */


)