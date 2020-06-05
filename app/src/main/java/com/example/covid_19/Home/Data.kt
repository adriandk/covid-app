package com.example.covid_19.Home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Data (
    var name : String? = null,
    var positif : String? = null,
    var sembuh : String? = null,
    var meninggal : String? = null
) : Parcelable