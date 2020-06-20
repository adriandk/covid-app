package com.example.covid_19.Search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import kotlinx.android.synthetic.main.provinsi.view.*

class AdapterProvinsi : RecyclerView.Adapter<AdapterProvinsi.ViewHolder>() {

    private val datacovid = ArrayList<DataProvinsi>()

    fun setData(items: ArrayList<DataProvinsi>) {
        datacovid.clear()
        datacovid.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProvinsi.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.provinsi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterProvinsi.ViewHolder, position: Int) {
        holder.bind(datacovid[position])
    }

    override fun getItemCount(): Int = datacovid.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(provinsiItems: DataProvinsi) {
            with(itemView) {
                nama_provinsi.text = provinsiItems.nama
                positif.text = provinsiItems.positif
                sembuh.text = provinsiItems.sembuh
                meninggal.text = provinsiItems.meniggal
            }
        }
    }

}