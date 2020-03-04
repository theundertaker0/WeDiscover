package com.kubo79.android.wediscover.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.kubo79.android.wediscover.state.State
import com.kubo79.android.wediscover.R
import com.kubo79.android.wediscover.state.RVStateAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_send.*
import org.json.JSONArray

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getStates()

    }

    private fun getStates(){
        FuelManager.instance.basePath = "https://wediscover.herokuapp.com"
        Fuel.get("/api/v1/states").responseString{ _, _, result->
            val datos=result.get()
            val arreglo = JSONArray(datos)
            var states = ArrayList<State>()
            if (arreglo.length() == 0) {
                Toast.makeText(context,"No hay Estados que mostrar", Toast.LENGTH_LONG)
            }
            states.clear()

            for (i in 0 until arreglo.length()) {
                val obj = arreglo.getJSONObject(i)
                states.add(State(obj.getInt("id"),obj.getString("name"),obj.getString("short_name"),obj.getString("image"),obj.getString("description"),obj.getString("biosecurity"),obj.getString("weather"),obj.getString("police_number"),obj.getString("firemen_number"),obj.getString("medical_number"),obj.getString("government_number"),obj.getString("lat"),obj.getString("lng"),obj.getString("ext1"),obj.getString("ext2"),obj.getString("created_at"),obj.getString("updated_at"),obj.getString("deleted_at")))
            }
            stateRecyclerView.layoutManager = LinearLayoutManager(activity)
            stateRecyclerView.hasFixedSize()
            stateRecyclerView.adapter = RVStateAdapter(states)

            println("hola")

        }

    }

}