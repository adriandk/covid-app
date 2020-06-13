package com.example.covid_19.international

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataInternational(
    var positif: String? = null,
    var sembuh: String? = null,
    var meninggal: String? = null
) : Parcelable