package com.example.covid_19.international

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import kotlinx.android.synthetic.main.items.view.*

class AdapterIntenational : RecyclerView.Adapter<AdapterIntenational.ViewHolder>() {

    private val dataInter = ArrayList<DataInternational>()

    fun setData(item: ArrayList<DataInternational>) {
        dataInter.clear()
        dataInter.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterIntenational.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataInter.size

    override fun onBindViewHolder(holder: AdapterIntenational.ViewHolder, position: Int) {
        holder.bind(dataInter[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataInterCovid: DataInternational) {
            with(itemView) {
                text_positif.text = dataInterCovid.positif
                text_meninggal.text = dataInterCovid.meninggal
                text_sembuh.text = dataInterCovid.sembuh
            }
        }
    }

}