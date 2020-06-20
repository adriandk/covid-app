package com.example.covid_19.Search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class GetAPIProvinsi : ViewModel() {

    val listData = MutableLiveData<ArrayList<DataProvinsi>>()

    internal fun setData() {
        val client = AsyncHttpClient()
        val data = ArrayList<DataProvinsi>()
        val url = "https://api.kawalcorona.com/indonesia/provinsi/"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val fetchdata = JSONArray(String(responseBody))
                    for (i in 0 until fetchdata.length()) {
                        val listprovinsi = fetchdata.getJSONObject(i)
                        val dataprovinsi = DataProvinsi()
                        dataprovinsi.nama =
                            listprovinsi.getJSONObject("attributes").getString("Provinsi")
                        dataprovinsi.positif =
                            listprovinsi.getJSONObject("attributes").getString("Kasus_Posi")
                        dataprovinsi.sembuh =
                            listprovinsi.getJSONObject("attributes").getString("Kasus_Semb")
                        dataprovinsi.meniggal =
                            listprovinsi.getJSONObject("attributes").getString("Kasus_Meni")
                        data.add(dataprovinsi)
                    }
                    listData.postValue(data)
                } catch (ex: Exception) {
                    Log.d("Error", ex.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("Error", error.toString())
            }

        })
    }

    internal fun getData(): LiveData<ArrayList<DataProvinsi>> {
        return listData
    }

}