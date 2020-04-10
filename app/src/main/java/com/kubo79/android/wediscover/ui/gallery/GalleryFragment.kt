package com.kubo79.android.wediscover.ui.gallery


import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.fondesa.kpermissions.PermissionStatus
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.request.PermissionRequest
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.kubo79.android.wediscover.ForWebView
import com.kubo79.android.wediscover.R
import com.kubo79.android.wediscover.location.RVLocationAdapter
import com.kubo79.android.wediscover.maps.MapsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.json.JSONArray


class GalleryFragment : Fragment(), PermissionRequest.Listener {
    lateinit var datos:String
    private val request by lazy {
        permissionsBuilder(Manifest.permission.CALL_PHONE).build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        request.addListener(this)
        request.send()
        val id=requireArguments().getInt("id")
        getLocations(id) //Llamamos a las localidades del estado
        val bio=arguments?.getString("biosecurity")
        val weather=arguments?.getString("weather")
        val desc=arguments?.getString("description")
        txtStateName.text=arguments?.getString("name")
        //Para permitir las imágenes del richtext


        btnVerDescripcion.isEnabled = desc!="null"
        btnVerClima.isEnabled=weather!="null"
        btnVerBio.isEnabled=bio!="null"

        btnVerDescripcion.setOnClickListener{
            val intent=Intent(context,ForWebView::class.java)
            intent.putExtra("content",desc)
            startActivity(intent)
        }

        btnVerClima.setOnClickListener{

            val intent=Intent(context,ForWebView::class.java)
            intent.putExtra("content",weather)
            startActivity(intent)
        }

        btnVerBio.setOnClickListener{
            val intent=Intent(context,ForWebView::class.java)
            intent.putExtra("content",bio)
            startActivity(intent)
        }

        Picasso.get().load("https://wediscoverfinal.s3.amazonaws.com/"+arguments?.getString("image")).into(imgState)


        //Llamadas telefónicas
        txtPolice.setOnClickListener {
            val phone=arguments?.getString("police")
            if(phone!="null"){
                val intent:Intent= Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
                startActivity(intent)
            }else{
                Toast.makeText(context,"No existe número telefónico registrado",Toast.LENGTH_LONG).show()
            }

        }

        txtMedical.setOnClickListener {
            val phone=arguments?.getString("medical")
            if(phone!="null"){
                val intent:Intent= Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
                startActivity(intent)
            }else{
                Toast.makeText(context,"No existe número telefónico registrado",Toast.LENGTH_LONG).show()
            }
        }

        txtFiremen.setOnClickListener {
            val phone=arguments?.getString("firemen")
            if(phone!="null"){
                val intent:Intent= Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
                startActivity(intent)
            }else{
                Toast.makeText(context,"No existe número telefónico registrado",Toast.LENGTH_LONG).show()
            }
        }

        txtGovernment.setOnClickListener {
            val phone=arguments?.getString("government")
            if(phone!="null"){
                val intent:Intent= Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
                startActivity(intent)
            }else{
                Toast.makeText(context,"No existe número telefónico registrado",Toast.LENGTH_LONG).show()
            }
        }

        //Botón para llamar al mapa
        btnVerEnMapa.setOnClickListener {
            val intent=Intent(context,MapsActivity::class.java)
            intent.putExtra("locations",datos)
            startActivity(intent)
        }


    }


    fun getLocations(id:Int){
        FuelManager.instance.basePath = "https://wediscover.herokuapp.com"
        Fuel.get("/api/v1/$id/locations").responseString{ _, _, result->
            datos=result.get()
            val arreglo = JSONArray(datos)
            var locations = ArrayList<com.kubo79.android.wediscover.location.Location>()
            if (arreglo.length() == 0) {
                Toast.makeText(context,"No hay Localidades que mostrar", Toast.LENGTH_LONG)
            }else{
                locations.clear()


                for (i in 0 until arreglo.length()) {
                    val obj = arreglo.getJSONObject(i)
                    locations.add(com.kubo79.android.wediscover.location.Location(
                        obj.getInt("id"),
                        obj.getString("name"),
                        obj.getString("short_description"),
                        obj.getString("image"),
                        "n",
                        "n",
                        "n",
                        "n",
                        "n",
                        "n",
                        "n",
                        "n",
                        "n",
                        "n",
                        "n",
                        obj.getString("lat"),
                        obj.getString("lng"),
                        "n",
                        "n",
                        "n",
                        "n",
                        ""
                        )
                    )
                }

                locationRecyclerView.layoutManager = GridLayoutManager(activity,2)
                locationRecyclerView.hasFixedSize()
                locationRecyclerView.adapter = RVLocationAdapter(locations)
            }


        }
    }

    override fun onPermissionsResult(result: List<PermissionStatus>) {
        when {

        }
    }

}