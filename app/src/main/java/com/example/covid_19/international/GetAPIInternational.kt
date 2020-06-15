package com.example.covid_19.international

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid_19.HomeActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class GetAPIInternational : ViewModel() {
    val dataInternational = MutableLiveData<ArrayList<DataInternational>>()

    internal fun setData() {
        val client = AsyncHttpClient()
        val data = ArrayList<DataInternational>()
        val API = "https://corona.lmao.ninja/v2/all"

        client.get(API, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val getData = JSONObject(String(responseBody))
                    val itemData = DataInternational()
                    itemData.positif = getData.getString("cases")
                    itemData.meninggal = getData.getString("deaths")
                    itemData.sembuh = getData.getString("recovered")
                    data.add(itemData)
                    dataInternational.postValue(data)
                } catch (ex: Exception) {
                    Log.d("Error Data Inter", ex.toString())
//                    Toast.makeText(InternationalData(), "Refresh", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("Error Data Inter", error.toString())
//                Toast.makeText(InternationalData(), "Refresh", Toast.LENGTH_LONG).show()
            }

        })

    }

    internal fun getData(): LiveData<ArrayList<DataInternational>> {
        return dataInternational
    }
}