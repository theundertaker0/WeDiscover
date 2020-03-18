package com.kubo79.android.wediscover.state

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kubo79.android.wediscover.R
import com.kubo79.android.wediscover.ui.gallery.GalleryFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.state_adapter_item_layout.view.*


class RVStateAdapter(private val userList: ArrayList<State>) : RecyclerView.Adapter<RVStateAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.state_adapter_item_layout, p0, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItems(userList[p1])
       // p0.name?.text = userList[p1].name
       //Picasso.get().load("https://wediscover.herokuapp.com/images/states/"+userList[p1].image).into(p0.image)
       // println("Cargo");
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       fun bindItems(item:State){
            itemView.txtName.text=item.name
           Picasso.get().load("https://wediscover.herokuapp.com/images/states/"+item.image).into(itemView.imgState)
           itemView.setOnClickListener {
               v: View? ->
               var bundle= bundleOf(
                   Pair("id",item.id),
                   Pair("name",item.name),
                   Pair("description",item.description),
                   Pair("police",item.police_number),
                   Pair("firemen",item.firemen_number),
                   Pair("medical",item.medical_number),
                   Pair("government",item.government_number),
                   Pair("biosecurity",item.biosecurity),
                   Pair("weather",item.weather),
                   Pair("image",item.image)
               )

               v?.findNavController()?.navigate(R.id.nav_gallery,bundle)

           }
       }

    }
}