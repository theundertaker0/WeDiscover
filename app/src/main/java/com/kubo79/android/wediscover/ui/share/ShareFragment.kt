package com.kubo79.android.wediscover.ui.share

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kubo79.android.wediscover.ForWebView
import com.kubo79.android.wediscover.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_share.*
import kotlinx.android.synthetic.main.fragment_share.view.*
import org.json.JSONObject


class ShareFragment : Fragment(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    lateinit var mapView:MapView
    private lateinit var obj:JSONObject
    private  var idLoc: Int=0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_share, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        idLoc=requireArguments().getInt("id")
        getLocation(idLoc)



        btnBio.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("biodiversity"))
            startActivity(intent)
        }

        btnAmbiental.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("environmental"))
            startActivity(intent)
        }
        btnCultura.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("culture"))
            startActivity(intent)
        }
        btnArqueologia.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("archeology"))
            startActivity(intent)
        }

        btnHistoria.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("history"))
            startActivity(intent)
        }

        btnEconomia.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("economy"))
            startActivity(intent)
        }

        btnDesarrollo.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("sustainable_development"))
            startActivity(intent)
        }

        btnDemografia.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("demography"))
            startActivity(intent)
        }

        btnGastronomia.setOnClickListener{

            val intent= Intent(context, ForWebView::class.java)
            intent.putExtra("content",obj.getString("gastronomy"))
            startActivity(intent)
        }

        btnDescripcion.setOnClickListener {
            val intent=Intent(context,ForWebView::class.java)
            intent.putExtra("content",obj.getString("description"))
            startActivity(intent)
        }


    }

    fun getLocation(id:Int){
        var datos:String

        FuelManager.instance.basePath = "https://wediscover.herokuapp.com"
        Fuel.get("/api/v1/locations/$idLoc").responseString { _, _, result ->
            datos = result.get()
            obj=JSONObject(datos)
            Picasso.get().load("https://wediscoverfinal.s3.amazonaws.com/${obj.getString("image")}").into(imgLocation)
            txtLocationName.text=obj.getString("name")
            txtShortDesc.text=obj.getString("short_description")
            btnBio.isEnabled=obj.getString("biodiversity")!="null"
            btnAmbiental.isEnabled=obj.getString("environmental")!="null"
            btnCultura.isEnabled=obj.getString("culture")!="null"
            btnArqueologia.isEnabled=obj.getString("archeology")!="null"
            btnHistoria.isEnabled=obj.getString("history")!="null"
            btnEconomia.isEnabled=obj.getString("economy")!="null"
            btnDesarrollo.isEnabled=obj.getString("sustainable_development")!="null"
            btnDemografia.isEnabled=obj.getString("demography")!="null"
            btnGastronomia.isEnabled=obj.getString("gastronomy")!="null"
            btnDescripcion.isEnabled=obj.getString("description")!="null"
            mapView= locationMap as MapView
            mapView.onCreate(null)
            mapView.getMapAsync(this)

        }
    }

   override fun onMapReady(googleMap: GoogleMap) {
       mMap = googleMap

        val centroMapa = LatLng(requireArguments().getString("lat").toDouble(),requireArguments().getString("lng").toDouble())
       mMap.addMarker(
           MarkerOptions().position(centroMapa).title(requireArguments().getString("name")))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centroMapa))
       mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(centroMapa.latitude,centroMapa.longitude),9.0F),1500,null)
    }
}