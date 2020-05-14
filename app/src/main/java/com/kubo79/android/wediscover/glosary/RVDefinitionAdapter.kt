package com.kubo79.android.wediscover.glosary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kubo79.android.wediscover.Faq
import com.kubo79.android.wediscover.R

class RVDefinitionAdapter( private val userList: ArrayList<Definition>) : RecyclerView.Adapter<RVDefinitionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.definition_adapter_item_layout, p0, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.word.text = userList[p1].word
        p0.definition.text = userList[p1].definition
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val word = itemView.findViewById<TextView>(R.id.txtWord)!!
        val definition = itemView.findViewById<TextView>(R.id.txtDefinition)!!

    }
}