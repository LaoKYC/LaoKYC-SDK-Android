package com.codecamp.laokycmodule.repositories

import com.codecamp.laokycmodule.services.IOIDCConfig

class OIDCConfig : IOIDCConfig {

    override var ClientID = ""

    override var ClientSecret = ""

    override var AUTHORIZSTION_END_POINT_URI = ""

    override var REDIRECT_URI = ""

    override var RESPONSE_TYPE = ""

    override var SCOPE = ""

    override var TOKEN_END_POINT_URI = ""
}