package com.kubo79.android.wediscover.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kubo79.android.wediscover.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.location_adapter_item_layout.view.*

class RVLocationAdapter(private val userList: ArrayList<Location>) : RecyclerView.Adapter<RVLocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.location_adapter_item_layout, p0, false)
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
        fun bindItems(item:Location){
            itemView.txtName.text=item.name
            itemView.txtShortDesc.text=item.short_description
            Picasso.get().load("https://wediscoverfinal.s3.amazonaws.com/"+item.image).into(itemView.imgLocation)
            itemView.setOnClickListener {
                    v: View? ->
                var bundle= bundleOf(
                    Pair("id",item.id),
                    Pair("name",item.name),
                    Pair("lat",item.lat),
                    Pair("lng",item.lng)
                )



                v?.findNavController()?.navigate(R.id.nav_share,bundle)

            }
        }

    }
}