package com.codecamp.laokycmodule

import com.codecamp.laokycmodule.repositories.SingleSignOn
import org.junit.Test

class MDSSOP1 {
    private val signOn: SingleSignOn = TODO()

    @Test
    fun SendAPIRequestForVerify(){
        val result = signOn.CheckAuthState()

        assert(result.IsSuccess)
    }
}