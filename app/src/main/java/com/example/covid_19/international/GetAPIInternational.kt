package com.example.covid_19.international

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import java.lang.Exception

class GetAPIInternational : ViewModel() {
    val dataInternational = MutableLiveData<ArrayList<DataInternational>>()

    internal fun setData() {
        val client = AsyncHttpClient()
        val data = ArrayList<DataInternational>()
        val API = "https://api.kawalcorona.com/positif/"

        client.get(API, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {

                } catch (ex: Exception) {
                    Log.d("Error Data Inter", ex.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("Error Data Inter", error.toString())
            }

        })

    }

    internal fun getData(): LiveData<ArrayList<DataInternational>> {
        return dataInternational
    }
}