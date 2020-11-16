package com.codecamp.laokycmodule.repositories

import android.content.Context
import android.content.res.Resources
import com.codecamp.laokycmodule.services.IOIDCConfig
import com.codecamp.laokycmodule.services.IRegisterConfigService
import org.json.JSONArray
import org.json.JSONObject
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

    override fun RegisterConfigBuilder(context: Context, jsonObject: JSONObject) {
        val _AUTHORIZSTION_END_POINT_URI = jsonObject.getString("AUTHORIZSTION_END_POINT_URI")
        val _CLIENT_ID = jsonObject.getString("CLIENT_ID")
        val _CLIENT_SECRET = jsonObject.getString("CLIENT_SECRET")
        val _REDIRECT_URI = jsonObject.getString("REDIRECT_URI")
        val _RESPONSE_TYPE = jsonObject.getString("RESPONSE_TYPE")
        val _SCOPE = jsonObject.getString("SCOPE")
        val _TOKEN_END_POINT_URI = jsonObject.getString("TOKEN_END_POINT_URI")

        config.ClientID = _CLIENT_ID.toString()
        config.ClientSecret = _CLIENT_SECRET.toString()
        config.AUTHORIZSTION_END_POINT_URI = _AUTHORIZSTION_END_POINT_URI.toString()
        config.REDIRECT_URI = _REDIRECT_URI.toString()
        config.RESPONSE_TYPE = _RESPONSE_TYPE.toString()
        config.SCOPE = _SCOPE.toString()
        config.TOKEN_END_POINT_URI = _TOKEN_END_POINT_URI.toString()
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