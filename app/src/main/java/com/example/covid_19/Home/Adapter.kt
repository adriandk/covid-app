package com.example.covid_19.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import kotlinx.android.synthetic.main.items.view.*

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>(){

    private val covid = ArrayList<Data>()

    fun setData (items : ArrayList<Data>){
        covid.clear()
        covid.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(covid[position])
    }

    override fun getItemCount(): Int = covid.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(coviditems : Data){
            with(itemView){
                text_positif.text = coviditems.positif
                text_meninggal.text = coviditems.meninggal
                text_sembuh.text = coviditems.sembuh
            }
        }
    }
}