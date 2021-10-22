package com.codecamp.laokycmodule.repositories

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.codecamp.laokycmodule.dtos.OIDCRequest
import com.codecamp.laokycmodule.dtos.OTPRequest
import com.codecamp.laokycmodule.dtos.OTPResponse
import com.codecamp.laokycmodule.model.ModelOTP
import com.codecamp.laokycmodule.services.IOIDCConfig
import com.codecamp.laokycmodule.services.IOIDCService
import com.codecamp.laokycmodule.services.IOTPService
import com.codecamp.laokycmodule.util.NetworkUtil
import com.codecamplao.laokycmodule.R
import com.google.gson.Gson
import com.gov.mpt.laokyclib.utils.ProgressDialogUtil
import org.json.JSONObject

class OTPServices(var authLogin : IOIDCService) : IOTPService {


    var dialogLaoKYC: Dialog? = null
    var etLoginPhonenumber: EditText? = null
    var btnLoginSubmit: Button? = null
    var ivLoginMainClose: ImageView? = null
    var dialog: ProgressDialogUtil? = null
    var dialogPop : ProgressDialogUtil? = null
    var _OperatorName : String? = null
    var _OperatorDB : String? = null

    override fun RequestOTP(Request: OTPRequest  , Callback: (OTPResponse) -> Unit) {


                /*val service = ServiceVolley()
                val apiController = APIController(service)

                val urlPath = "example_endpoint"
                val accessToken = "jhklvcbhfiugh8r348u3rhrifhhf934f"
                val paramsVolley = JSONObject()
                paramsVolley.put("email", "foo@email.com")
                paramsVolley.put("password", "barpass")

                apiController.post(urlPath , accessToken, paramsVolley) { response ->
                    // Parse the result
                }*/


                dialogPop = ProgressDialogUtil(Request.activity)

                if (Request.Phone == null || Request.Phone.isEmpty()) {
                    val result = OTPResponse(300, "Phone is empty", false)
                    Callback.invoke(result)
                }

                if (Request.Device == null || Request.Device.isEmpty()) {
                    val result = OTPResponse(301, "Device is empty", false)
                    Callback.invoke(result)

                }

                dialogPop!!.showDialogProgress(  "ຂໍລະຫັດຜ່ານ...")

                val service = ServiceVolley()
                val apiController = APIController(service)

                val urlPath = Request.URL_API
                val accessToken = ""
                val paramsVolley = JSONObject()
                paramsVolley.put("phone", Request.Phone)
                paramsVolley.put("Device", Request.Device)

                apiController.post(Request.activity , urlPath , accessToken, paramsVolley) { response , error ->

                    dialogPop!!.dismissDialogProgress()

                    if (response != null ) {
                        val strResp = response.toString()
                        val urlBodyGson = Gson().fromJson(strResp, ModelOTP::class.java)
                        val result = OTPResponse(200, urlBodyGson.message, true)

                        Callback.invoke(result)
                    } else {
                        val status = error!!.networkResponse?.statusCode
                        if (error!!.message != null) {
                            val result = OTPResponse(status!!, error!!.message!!, false)
                            Callback.invoke(result)
                        } else {
                            val result = OTPResponse(status!!, "Error null OTP Service", false)
                            Callback.invoke(result)
                        }


                    }
                }


              /*  val queue = Volley.newRequestQueue(Request.activity)

                val params = HashMap<String, String>()
                params["phone"] = Request.Phone
                params["Device"] = Request.Device
                val jsonObject = JSONObject(params as Map<*, *>)


                val req: JsonObjectRequest =
                    object : JsonObjectRequest(
                        Method.POST, Request.URL_API,
                        jsonObject, Response.Listener { response ->

                            val strResp = response.toString()
                            val urlBodyGson = Gson().fromJson(strResp, ModelOTP::class.java)
                            val result = OTPResponse(200, urlBodyGson.message, true)

                            dialogPop!!.dismissDialogProgress()

                            Callback.invoke(result)

                        }, Response.ErrorListener { error ->
                            val status = error.networkResponse?.statusCode
                            val result = OTPResponse(status!!, error.message.toString(), false)
                            Callback.invoke(result)
                        }) {
                        @Throws(AuthFailureError::class)
                        override fun getHeaders(): Map<String, String> {
                            val headers =
                                HashMap<String, String>()
                            //headers.put("Content-Type", "application/json");
                            // headers["Content_Type"] = "application/json"
                            // headers["Authorization"] = "bearer $Request.AccessToken"
                            return headers
                        }
                    }
                queue!!.add(req)*/

    }



    override fun showDialog(request : OTPRequest , redirectActivity : Class<*>) {

        dialogLaoKYC = Dialog(request.activity)
        dialog = ProgressDialogUtil(request.activity)
        dialogLaoKYC!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogLaoKYC!!.setCancelable(false)
        dialogLaoKYC!!.setContentView(R.layout.activity_dialog)

        ivLoginMainClose = dialogLaoKYC!!.findViewById(R.id.ivLoginMainClose)
        etLoginPhonenumber = dialogLaoKYC!!.findViewById(R.id.etLoginPhonenumber)
        btnLoginSubmit = dialogLaoKYC!!.findViewById(R.id.btnLoginSubmit)

        etLoginPhonenumber!!.requestFocus()

        dialogLaoKYC!!.show()

        ivLoginMainClose!!.setOnClickListener({
            dialogLaoKYC!!.dismiss()
        })

        btnLoginSubmit!!.setOnClickListener {
            val status: Int = NetworkUtil.getConnectivityStatusString(request.activity)
            if (status == 0) {

                dialog!!.showKAlertDialog("ກະລຸນາເຊື່ອມຕໍ່ອິນເຕີເນັດ.", "warning")

                return@setOnClickListener
            }


            if (etLoginPhonenumber!!.text.toString().trim().length == 0) {
                dialog!!.showKAlertDialog("ກະລຸນາປ້ອນເບີໂທລະສັບຂອງທ່ານ.", "warning")
                etLoginPhonenumber!!.requestFocus()
                return@setOnClickListener
            }

            var _OperatorN = CheckOperatorByPhonenumber(etLoginPhonenumber!!.getText().toString())

            if (_OperatorN.equals("")) {
                dialog!!.showKAlertDialog(
                    "ກະລຸນປ້ອນເບີໂທລະສັບຂອງທ່ານໃຫ້ຖືກຕ້ອງ (ເລີ່ມຕົ້ນດ້ວຍ 20 ຫຼື 30)",
                    "warning"
                )

                return@setOnClickListener
            }

            if (etLoginPhonenumber!!.getText().toString().substring(0, 2).equals(
                    "20"
                ) || etLoginPhonenumber!!.getText().toString().substring(0, 2).equals("30")
            ) else {
                dialog!!.showKAlertDialog(
                    "ກະລຸນປ້ອນເບີໂທລະສັບຂອງທ່ານໃຫ້ຖືກຕ້ອງ (ເລີ່ມຕົ້ນດ້ວຍ 20 ຫຼື 30)",
                    "warning"
                )

                val pos: Int = etLoginPhonenumber!!.getText().length
                etLoginPhonenumber!!.setSelection(pos)
                etLoginPhonenumber!!.requestFocus()
                return@setOnClickListener

            }

            dialogLaoKYC!!.dismiss()

                // API Request OTP
                RequestOTP(OTPRequest(etLoginPhonenumber!!.getText().toString() , request.Device , request.URL_API ,
                    request.activity) ) { result ->

                    if (result.IsSuccess == true) {

                        var oidcRequest = OIDCRequest( request.activity, etLoginPhonenumber!!.getText().toString() , "Android" , "login" , redirectActivity)
                        dialogPop!!.dismissDialogProgress()
                        authLogin.OIDCAuthLogin(oidcRequest)

                    } else {
                        Toast.makeText(request.activity , result.Code.toString() + " | " + result.Message , Toast.LENGTH_LONG).show()
                    }


                }



            }


    }
    private fun CheckOperatorByPhonenumber(_TextNumber: String): String? {
        try {
            return if (_TextNumber.startsWith("20") == true) { // 20 - Pre-paid or Post-Paid Number
                if (_TextNumber.length == 10) {
                    if (_TextNumber.startsWith("205")) {
                        _OperatorName = "LTC"
                        _OperatorName
                    } else if (_TextNumber.startsWith("202")) {
                        _OperatorName = "ETL"
                        _OperatorName
                    } else if (_TextNumber.startsWith("207")) {
                        _OperatorName = "TPlus"
                        _OperatorName
                    } else if (_TextNumber.startsWith("209")) {
                        _OperatorName = "Unitel"
                        _OperatorName
                    } else if (_TextNumber.startsWith("208")) {
                        _OperatorName = "Sky"
                        _OperatorName
                    } else if (_TextNumber.startsWith("206")) {
                        _OperatorName = "LAOSAT"
                        _OperatorName
                    } else if (_TextNumber.startsWith("203")) {
                        _OperatorName = "Planet"
                        _OperatorName
                    } else {
                        _OperatorName = ""
                        _OperatorName
                    }
                } else {
                    _OperatorDB = ""
                    ""
                }
            } else if (_TextNumber.startsWith("30") == true) { // 30 -  WinPhone Number
                if (_TextNumber.length == 9) {
                    if (_TextNumber.startsWith("305")) {
                        _OperatorName = "LTC"
                        _OperatorName
                    } else if (_TextNumber.startsWith("302")) {
                        _OperatorName = "ETL"
                        _OperatorName
                    } else if (_TextNumber.startsWith("307")) {
                        _OperatorName = "TPlus"
                        _OperatorName
                    } else if (_TextNumber.startsWith("309")) {
                        _OperatorName = "Unitel"
                        _OperatorName
                    } else if (_TextNumber.startsWith("308")) {
                        _OperatorName = "Sky"
                        _OperatorName
                    } else if (_TextNumber.startsWith("306")) {
                        _OperatorName = "LAOSAT"
                        _OperatorName
                    } else if (_TextNumber.startsWith("303")) {
                        _OperatorName = "Planet"
                        _OperatorName
                    } else if (_TextNumber.startsWith("304")) {
                        _OperatorName = "Unitel"
                        _OperatorName
                    } else {
                        _OperatorName = ""
                        _OperatorName
                    }
                } else {
                    _OperatorName = ""
                    _OperatorName
                }
            } else {
                if (_TextNumber.length == 8) { // 5 - 2- 7 -9 All Operator
                    if (_TextNumber.startsWith("5")) {
                        _OperatorName = "LTC"
                        _OperatorName
                    } else if (_TextNumber.startsWith("2")) {
                        _OperatorName = "ETL"
                        _OperatorName
                    } else if (_TextNumber.startsWith("7")) {
                        _OperatorName = "TPlus"
                        _OperatorName
                    } else if (_TextNumber.startsWith("9")) {
                        _OperatorName = "Unitel"
                        _OperatorName
                    } else if (_TextNumber.startsWith("8")) {
                        _OperatorName = "Sky"
                        _OperatorName
                    } else if (_TextNumber.startsWith("6")) {
                        _OperatorName = "LAOSAT"
                        _OperatorName
                    } else if (_TextNumber.startsWith("3")) {
                        _OperatorName = "Planet"
                        _OperatorName
                    } else if (_TextNumber.startsWith("4")) {
                        _OperatorName = "Unitel"
                        _OperatorName
                    } else {
                        _OperatorDB = ""
                        _OperatorName
                    }
                } else if (_TextNumber.length == 7) {
                    if (_TextNumber.startsWith("5")) {
                        _OperatorName = "LTC"
                        _OperatorName
                    } else if (_TextNumber.startsWith("2")) {
                        _OperatorName = "ETL"
                        _OperatorName
                    } else if (_TextNumber.startsWith("7")) {
                        _OperatorName = "TPlus"
                        _OperatorName
                    } else if (_TextNumber.startsWith("9")) {
                        _OperatorName = "Unitel"
                        _OperatorName
                    } else if (_TextNumber.startsWith("8")) {
                        _OperatorName = "Sky"
                        _OperatorName
                    } else if (_TextNumber.startsWith("6")) {
                        _OperatorName = "LAOSAT"
                        _OperatorName
                    } else if (_TextNumber.startsWith("3")) {
                        _OperatorName = "Planet"
                        _OperatorName
                    } else if (_TextNumber.startsWith("4")) {
                        _OperatorName = "Unitel"
                        _OperatorName
                    } else {
                        _OperatorName
                    }
                } else {
                    _OperatorName = ""
                    _OperatorName
                }
            }
        } catch (e: java.lang.Exception) {
        }
        return null
    }
}

