package com.kubo79.android.wediscover.ui.send


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.kubo79.android.wediscover.Faq
import com.kubo79.android.wediscover.R
import com.kubo79.android.wediscover.RvAdapter
import kotlinx.android.synthetic.main.fragment_send.*
import org.json.JSONArray



class SendFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       getFaqs()

    }


    private fun getFaqs(){
        FuelManager.instance.basePath = "https://wediscover.herokuapp.com"
        Fuel.get("/api/v1/faqs").responseString{_,_,result->
            val datos=result.get()
            val arreglo = JSONArray(datos)
            var faqs = ArrayList<Faq>()
            if (arreglo.length() == 0) {
                Toast.makeText(context,"No hay preguntas frecuentes que mostrar",Toast.LENGTH_LONG).show()
            }
            faqs.clear()

            for (i in 0 until arreglo.length()) {
                val obj = arreglo.getJSONObject(i)
                faqs.add(Faq(obj.getInt("id"),obj.getString("question"),obj.getString("answer"),obj.getString("ext1"),obj.getString("ext2"),obj.getString("created_at"),obj.getString("updated_at"),obj.getString("deleted_at")))
            }
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.hasFixedSize()
            recyclerView.adapter = RvAdapter(faqs)

            println("hola")

        }

    }
}