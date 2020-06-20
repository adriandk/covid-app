package com.example.covid_19.Search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataProvinsi(
    var nama: String? = null,
    var positif: String? = null,
    var sembuh: String? = null,
    var meniggal: String? = null
) : Parcelable