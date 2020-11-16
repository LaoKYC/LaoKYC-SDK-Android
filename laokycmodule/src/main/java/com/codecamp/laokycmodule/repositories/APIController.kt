package com.codecamp.laokycmodule.repositories

import android.content.Context
import com.android.volley.VolleyError
import com.codecamp.laokycmodule.services.ServiceInterface
import org.json.JSONObject

class APIController constructor(serviceInjection: ServiceInterface): ServiceInterface {
    private val service: ServiceInterface = serviceInjection

    override fun post( context: Context , path: String ,accessToken: String, params: JSONObject, completionHandler: (response: JSONObject? , error: VolleyError?) -> Unit) {
        service.post( context , path ,accessToken , params, completionHandler)
    }

    override fun getJSONObject(context: Context , path: String, accessToken: String, params: JSONObject, completionHandler: (response: JSONObject? , error: VolleyError?) -> Unit) {
        service.getJSONObject( context , path , accessToken , params, completionHandler)
    }

    override fun getStringObject(context: Context , path: String, accessToken: String, completionHandler: (response: String? , error: VolleyError?) -> Unit) {
        service.getStringObject( context , path , accessToken , completionHandler)
    }


}