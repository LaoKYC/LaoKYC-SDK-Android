package com.codecamp.laokycmodule

import com.android.volley.ResponseDelivery
import com.codecamp.laokycmodule.repositories.SingleSignOn
import com.codecamp.laokycmodule.services.ISingleSignOn
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class MDSSOP1 {
    private val signOn: ISingleSignOn = SingleSignOn()
    private var mDelivery: ResponseDelivery? = null

    @Mock
    private val mMockNetwork: Mock? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        //mDelivery = ImmediateResponseDelivery()
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun SendAPIRequestForVerify(){

//        val result = signOn!!.CheckAuthState("32424234234234" , "https://gateway.sbg.la/" )
//        Assert.assertEquals(true , result.IsSuccess)
       // assert(result.IsSuccess)
    }


}