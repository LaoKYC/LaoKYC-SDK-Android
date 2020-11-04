package com.codecamp.laokycmodule.services

interface IOIDCConfig {

    var ClientID : String
    var ClientSecret : String
    var RESPONSE_TYPE : String
    var REDIRECT_URI : String
    var AUTHORIZSTION_END_POINT_URI : String
    var SCOPE : String
    var TOKEN_END_POINT_URI : String

}