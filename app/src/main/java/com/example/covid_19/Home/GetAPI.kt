package com.example.covid_19.Home

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
import java.lang.Exception

class GetAPI : ViewModel(){

    val listdata = MutableLiveData<ArrayList<Data>>()

    internal fun setData(){
        val client = AsyncHttpClient()
        val data = ArrayList<Data>()
//        val url = "https://api.kawalcorona.com/indonesia"

        val url = "https://covid19.mathdro.id/api/countries/Indonesia/confirmed"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val getdata = JSONArray(String(responseBody))
                    val items = Data()
                    items.name = getdata.getJSONObject(0).getString("countryRegion")
                    items.positif = getdata.getJSONObject(0).getString("confirmed")
                    items.sembuh = getdata.getJSONObject(0).getString("recovered")
                    items.meninggal = getdata.getJSONObject(0).getString("deaths")
                    data.add(items)
                    listdata.postValue(data)
                } catch (ex: Exception) {
                    Log.d("Exception", ex.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error.toString())
            }
        })
    }

    internal fun getData() : LiveData<ArrayList<Data>>{
        return listdata
    }

}