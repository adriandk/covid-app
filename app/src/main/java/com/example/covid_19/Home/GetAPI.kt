package com.example.covid_19.Home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        val url = "https://api.kawalcorona.com/indonesia"

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>, responseBody: ByteArray) {
                try {
                    val getdata = JSONArray(String(responseBody))
                    val items = Data()
                    items.name = getdata.getJSONObject(0).getString("name")
                    items.positif = getdata.getJSONObject(0).getString("positif")
                    items.sembuh = getdata.getJSONObject(0).getString("sembuh")
                    items.meninggal = getdata.getJSONObject(0).getString("meninggal")
                    data.add(items)
                    listdata.postValue(data)
                } catch (ex: Exception) {
//                    Log.d("Exception", ex.message.toString())
                    setData()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
//                Log.d("onFailure", error.message.toString())
                setData()
            }
        })
    }

    internal fun getData() : LiveData<ArrayList<Data>>{
        return listdata
    }
}