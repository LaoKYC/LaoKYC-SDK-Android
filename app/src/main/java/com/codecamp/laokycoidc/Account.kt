package com.codecamp.laokycoidc

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.ParameterizedType

@Parcelize
data class Account(var address: String, var zipCode: Int ) : Parcelable