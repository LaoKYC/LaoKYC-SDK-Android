package com.codecamp.laokycmodule.services

import android.content.Context
import com.android.volley.VolleyError
import org.json.JSONObject

interface ServiceInterface {
    fun post( context: Context , path: String ,accessToken: String, params: JSONObject, completionHandler: (response: JSONObject? , error: VolleyError?) -> Unit)

    fun getJSONObject( context: Context , path: String ,accessToken: String, params: JSONObject, completionHandler: (response: JSONObject? , error: VolleyError?) -> Unit)

    fun getStringObject( context: Context , path: String ,accessToken: String , completionHandler: (response: String? , error: VolleyError?) -> Unit )


}