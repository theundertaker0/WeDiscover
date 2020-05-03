package com.kubo79.android.wediscover.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kubo79.android.wediscover.R
import kotlinx.android.synthetic.main.fragment_tools.*

class ToolsFragment : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this).get(ToolsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val cont="<p>En <span style='color:#59A004;font-weight: bold;'>We</span><span style='color:#1B89F8;font-weight: bold;'>Discover</span> no sólo queremos que visites y explores los diferentes destinos y sus atractivos turísticos, ofrecemos una nueva forma de ver el turismo con base a información científica y descriptiva de los lugares, en donde podrás no sólo conocer sino entender cada sitio, su población, el medio natural, su historia y el contexto de cada localidad dentro de la península de Yucatán de una manera informada, responsable y segura</p>"
        wvNosotros.loadDataWithBaseURL("",cont,"text/html","utf-8","")
    }
}