package com.codecamp.laokycmodule.repositories

import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.codecamp.laokycmodule.dtos.CheckAuthStateRequest
import com.codecamp.laokycmodule.dtos.CheckAuthStateResponse
import com.codecamp.laokycmodule.services.ISingleSignOn


class SingleSignOn : ISingleSignOn {


    override fun CheckAuthState(Request : CheckAuthStateRequest, Callback: (CheckAuthStateResponse) -> Unit) {

        if (Request.AccessToken == null || Request.AccessToken.isEmpty()) {
            val result = CheckAuthStateResponse(300, "AccessToken is empty", false)
            Callback.invoke(result)
        }

        if (Request.URL_API == null || Request.URL_API.isEmpty()) {
            val result = CheckAuthStateResponse(301, "URL API is empty", false)
            Callback.invoke(result)

        }
        val queue = Volley.newRequestQueue(Request.activity)
        val req: JsonObjectRequest =
            object : JsonObjectRequest(
                Method.GET, Request.URL_API,
                null, Response.Listener { response ->
                    val result = CheckAuthStateResponse(200, "success", true)
                    Callback.invoke(result)

                }, Response.ErrorListener { error ->
                    val status = error.networkResponse?.statusCode
                    val result = CheckAuthStateResponse(status!!, error.message.toString(), false)
                    Callback.invoke(result)
                }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers =
                        HashMap<String, String>()
                    //headers.put("Content-Type", "application/json");
                    headers["Content_Type"] = "application/json"
                    headers["Authorization"] = "bearer $Request.AccessToken"
                    return headers
                }
            }
        queue!!.add(req)
    }
}