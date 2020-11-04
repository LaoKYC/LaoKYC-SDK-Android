package com.codecamp.laokycmodule.services

import android.content.Context
import android.content.Intent

interface IClaimService {

    var accessToken : String
    var firstName : String
    var familyName : String
    var picture : String
    var phoneNumber : String
    var preferredUsername : String
    var userID : String
    var gender : String

    fun ExtractClaims(context: Context , dataIntent : Intent )

}