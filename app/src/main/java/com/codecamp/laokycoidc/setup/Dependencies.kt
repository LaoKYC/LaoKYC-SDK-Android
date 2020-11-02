package com.codecamp.laokycoidc.setup

import com.codecamp.laokycmodule.repositories.SingleSignOn
import com.codecamp.laokycmodule.services.ISingleSignOn
import org.koin.dsl.module


val appDependencies = module {

        // Singleton (returns always the same unique instance of the object)
        single<ISingleSignOn> { SingleSignOn() }



}
