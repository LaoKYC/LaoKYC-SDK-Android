package com.codecamp.laokycmodule

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.Volley
import okhttp3.internal.Internal.instance


class MyApp : Application() {

    var Token: String? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        lateinit var Token: String
        private val TAG = MyApp::class.java.simpleName
        @get:Synchronized var instance: MyApp? = null
            private set
    }

    val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun <T> addToRequestQueue(request: Request<T>, tag: String) {
        request.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue?.add(request)
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        request.tag = TAG
        requestQueue?.add(request)
    }

    fun cancelPendingRequests(tag: Any) {
        if (requestQueue != null) {
            requestQueue!!.cancelAll(tag)
        }
    }


}