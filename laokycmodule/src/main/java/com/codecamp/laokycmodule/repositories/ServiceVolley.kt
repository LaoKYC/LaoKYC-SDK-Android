package com.codecamp.laokycmodule.repositories

import android.content.Context
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.codecamp.laokycmodule.services.ServiceInterface
import org.json.JSONObject

class ServiceVolley : ServiceInterface {

    override fun post(context : Context , path: String, accessToken: String , params: JSONObject,
        completionHandler: (response: JSONObject? , error: VolleyError?) -> Unit
    ) {

        val queue = Volley.newRequestQueue(context)

        val jsonObjReq = object : JsonObjectRequest(Method.POST, path, params,
            Response.Listener { response ->
                completionHandler(response , null)
            },
            Response.ErrorListener { error ->
                completionHandler( null , error)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                if (accessToken!= "") {
                    headers.put("Authorization", "bearer " + accessToken)
                }

                return headers
            }
        }

        queue.add(jsonObjReq)

    }


    override fun getJSONObject(context: Context , path: String, accessToken: String, params: JSONObject,
        completionHandler: (response: JSONObject? , error: VolleyError?) -> Unit
    ) {

        val queue = Volley.newRequestQueue(context)

        val getRequest = JsonObjectRequest(Request.Method.GET,path, params, Response.Listener { response ->
            completionHandler(response , null)
        },Response.ErrorListener { error ->
            completionHandler( null , error)
        })

        queue.add(getRequest)

    }


    override fun getStringObject(context: Context , path: String, accessToken: String,
        completionHandler: (response: String?,error: VolleyError?) -> Unit
    ) {

        val queue = Volley.newRequestQueue(context)

        val jsonRequest = object : StringRequest(
            Request.Method.GET, path  ,
            Response.Listener<String> { response ->
                completionHandler(response , null)
            },
            Response.ErrorListener { error ->
                completionHandler( null , error)
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content_Type"] = "application/json"
                if (accessToken!= "") {
                    headers.put("Authorization", "bearer " + accessToken)
                }
                return headers
            }
        }

        queue.add(jsonRequest)
    }



}