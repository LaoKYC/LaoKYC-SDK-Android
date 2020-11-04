package com.codecamp.laokycoidc.setup

import com.codecamp.laokycmodule.dtos.CheckAuthStateResponse
import com.codecamp.laokycmodule.repositories.*
import com.codecamp.laokycmodule.services.*
import org.koin.dsl.module


val appDependencies = module {

        // Singleton (returns always the same unique instance of the object)
        single<ISingleSignOn> { SingleSignOn() }
        single<IOTPService> { OTPServices(get()) }
        single<IOIDCService> { OIDCAuthLogin(get())}
        single<IOIDCConfig> { OIDCConfig()}
        single<IRegisterConfigService> { RegisterConfigService(get())}
        single<IClaimService> { ClaimService(get())}
}
