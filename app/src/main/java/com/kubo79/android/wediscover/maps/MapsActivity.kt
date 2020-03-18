package com.kubo79.android.wediscover.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.kubo79.android.wediscover.R
import org.json.JSONArray

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val bundle=intent.extras
        val locations=bundle?.getString("locations")


        // Add a marker in Sydney and move the camera
        val arreglo = JSONArray(locations)
        var pos:LatLng
        var marker:Marker
        //Centrar marcadores en el mapa
        val constructor= LatLngBounds.Builder()
        if (arreglo.length() == 0) {
            Toast.makeText(this,"No hay Localidades que mostrar", Toast.LENGTH_LONG).show()
        }else {
            for (i in 0 until arreglo.length()) {
                val obj = arreglo.getJSONObject(i)
                pos= LatLng(obj.getString("lat").toDouble(),obj.getString("lng").toDouble())
                marker=mMap.addMarker(MarkerOptions().position(pos).title(obj.getString("name")))
                constructor.include(marker.getPosition())
            }

            //Centrar marcadores en el mapa
           val limites=constructor.build()
            val ancho=resources.displayMetrics.widthPixels
            val alto=resources.displayMetrics.heightPixels
            val padding = (alto*0.25).toInt()
            val centrarMarcadores=CameraUpdateFactory.newLatLngBounds(limites,ancho,alto,padding)

            mMap.animateCamera(centrarMarcadores)
        }
    }
}
