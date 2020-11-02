package com.codecamp.laokycmodule.repositories

import android.R.attr.tag
import android.app.Activity
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.codecamp.laokycmodule.dtos.CheckAuthStateResponse
import com.codecamp.laokycmodule.services.ISingleSignOn
import org.json.JSONObject


class SingleSignOn : ISingleSignOn {

    private var result = CheckAuthStateResponse()

    override fun CheckAuthState(
        AccessToken: String,
        URL_API: String ,
    activity: Activity): CheckAuthStateResponse {


        if (AccessToken == null || AccessToken.isEmpty()) {
            result!!.Code = 300
            result!!.Message = "AccessToken is empty"
            result!!.IsSuccess = false
            return result
        }


        if (URL_API == null || URL_API.isEmpty()) {
            result!!.Code = 301
            result!!.Message = "URL API is empty"
            result!!.IsSuccess = false
            return result
        }


       /* val req: JsonObjectRequest =
            object : JsonObjectRequest(
                Method.GET, URL_API,
                null, Response.Listener { response ->
                    result = CheckAuthStateResponse(200, "Auth state success", true)
                }, Response.ErrorListener { error ->
                    val status = error.networkResponse?.statusCode
                    result = CheckAuthStateResponse(status!!, error.message.toString(), false)
                }) {
                *//**
                 * Passing some request headers
                 *//*
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers =
                        HashMap<String, String>()
                    //headers.put("Content-Type", "application/json");
                    headers["Content_Type"] = "application/json"
                    headers["Authorization"] = "bearer $AccessToken"
                    return headers
                }
            }
        requestQueue!!.add(req)*/

        val queue = Volley.newRequestQueue(activity)

        val jsonRequest = object : StringRequest(
            Request.Method.GET, URL_API ,
            Response.Listener<String> { response ->

                // code 200
                result!!.Code = 200
                result!!.Message = "Auth state success"
                result!!.IsSuccess = true
                //return@Listener
               // return result

            },
            Response.ErrorListener { error ->
                val status = error.networkResponse?.statusCode
                result!!.Code = status!!
                result!!.Message = error.message.toString()
                result!!.IsSuccess = false
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content_Type"] = "application/json"
                headers["Authorization"] = "bearer $AccessToken"
                return headers
            }
        }
        queue!!.add(jsonRequest)

        return result
    }

}