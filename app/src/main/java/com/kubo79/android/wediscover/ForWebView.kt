package com.kubo79.android.wediscover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_for_web_view.*


class ForWebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_web_view)

        val bundle=intent.extras
        val cont=bundle?.getString("content")

        wvContent.loadDataWithBaseURL("",cont,"text/html","utf-8","")
    }
}
