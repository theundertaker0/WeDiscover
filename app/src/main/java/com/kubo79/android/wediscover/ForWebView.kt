package com.kubo79.android.wediscover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_for_web_view.*


class ForWebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_web_view)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        val bundle=intent.extras
        val cont=bundle?.getString("content")

        wvContent.loadDataWithBaseURL("",cont,"text/html","utf-8","")
    }
}
