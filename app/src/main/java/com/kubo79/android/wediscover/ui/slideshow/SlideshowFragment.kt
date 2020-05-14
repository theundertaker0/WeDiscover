package com.kubo79.android.wediscover.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.kubo79.android.wediscover.Faq
import com.kubo79.android.wediscover.R
import com.kubo79.android.wediscover.RvAdapter
import com.kubo79.android.wediscover.glosary.Definition
import com.kubo79.android.wediscover.glosary.RVDefinitionAdapter
import kotlinx.android.synthetic.main.fragment_send.*
import kotlinx.android.synthetic.main.fragment_slideshow.*
import org.json.JSONArray

class SlideshowFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_slideshow,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDefinitions()

    }


    private fun getDefinitions(){
        FuelManager.instance.basePath = "https://wediscover.herokuapp.com"
        Fuel.get("/api/v1/definitions").responseString{ _, _, result->
            val datos=result.get()
            val arreglo = JSONArray(datos)
            var defs = ArrayList<Definition>()
            if (arreglo.length() == 0) {
                Toast.makeText(context,"No hay palabras que mostrar en el Glosario", Toast.LENGTH_LONG).show()
            }else{
                defs.clear()

                for (i in 0 until arreglo.length()) {
                    val obj = arreglo.getJSONObject(i)
                    defs.add(Definition(obj.getInt("id"),obj.getString("word"),obj.getString("definition"),obj.getString("ext1"),obj.getString("ext2"),obj.getString("created_at"),obj.getString("updated_at"),obj.getString("deleted_at")))
                }
                definitionRecyclerView.layoutManager = LinearLayoutManager(activity)
                definitionRecyclerView.hasFixedSize()
                definitionRecyclerView.adapter = RVDefinitionAdapter(defs)

                println("hola")
            }


        }

    }

}