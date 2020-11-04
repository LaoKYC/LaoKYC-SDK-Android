package com.codecamp.laokycmodule.repositories

import android.content.Context
import android.content.res.Resources
import com.codecamp.laokycmodule.services.IOIDCConfig
import com.codecamp.laokycmodule.services.IRegisterConfigService
import java.io.InputStream
import java.util.*

class RegisterConfigService(var config : IOIDCConfig) : IRegisterConfigService {

    override fun RegisterConfigBuilder(context:Context , resourceRaw : Int) {

        val _AUTHORIZSTION_END_POINT_URI: String = getConfigValue(context, resourceRaw , "AUTHORIZSTION_END_POINT_URI").toString()
        val _CLIENT_ID: String = getConfigValue(context, resourceRaw , "CLIENT_ID").toString()
        val _CLIENT_SECRET: String = getConfigValue(context, resourceRaw , "CLIENT_SECRET").toString()
        val _REDIRECT_URI: String = getConfigValue(context, resourceRaw , "REDIRECT_URI").toString()
        val _RESPONSE_TYPE: String = getConfigValue(context, resourceRaw , "RESPONSE_TYPE").toString()
        val _SCOPE: String = getConfigValue(context, resourceRaw , "SCOPE").toString()
        val _TOKEN_END_POINT_URI: String = getConfigValue(context, resourceRaw , "TOKEN_END_POINT_URI").toString()

        config.ClientID = _CLIENT_ID
        config.ClientSecret = _CLIENT_SECRET
        config.AUTHORIZSTION_END_POINT_URI = _AUTHORIZSTION_END_POINT_URI
        config.REDIRECT_URI = _REDIRECT_URI
        config.RESPONSE_TYPE = _RESPONSE_TYPE
        config.SCOPE = _SCOPE
        config.TOKEN_END_POINT_URI = _TOKEN_END_POINT_URI

    }

    private fun getConfigValue(context:Context , resourceRaw : Int , name : String) : String? {

        val resources: Resources = context.getResources()
        try {
            val rawResource: InputStream = resources.openRawResource(resourceRaw)
            val properties = Properties()
            properties.load(rawResource)
            return properties.getProperty(name)
        } catch (e: Resources.NotFoundException) {

        }
        return null
    }

}