package com.codecamp.laokycmodule.services

import android.content.Context
import com.codecamp.laokycmodule.repositories.RegisterConfigService
import org.json.JSONObject
import java.util.*

interface IRegisterConfigService {
    fun RegisterConfigBuilder(context: Context, resourceRaw : Int)

    fun RegisterConfigBuilder(context: Context , jsonObject: JSONObject)
}