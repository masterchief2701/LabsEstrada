package com.example.labestrada

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import com.example.labestrada.uitel.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

/*
class MainActivity : AppCompatActivity() {

    //FUNCION - Cuando se corre la Activity por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Refresh
        swipeRefresh.setOnRefreshListener {
            webView.reload()
        }

        //Soporte para navegadores
       // webView.webChromeClient = object : WebChromeClient(){

        //}

        webView.webViewClient = object : WebViewClient(){

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                //searchView.setQuery(url, false)

                swipeRefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                swipeRefresh.isRefreshing = false
            }


        }


        //Segun este codigo es el loading
        //val loading = LoadingDialog(this )
        //loading.startLoading()
        //val handler = Handler()
        //handler.postDelayed(object :Runnable{
        //    override fun run() {
        //        loading.isDismiss()
        //    }

        //},3000) //El tiempo del loading en cargar el contenido

        //Ocultar el ActionBar
        supportActionBar?.hide();

        //Habilitar el soporte a Javascript
        var webView = findViewById<WebView>(R.id.webView)
        var webSettings: WebSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true

        //Establecemos la URL a donde apunta el WebView
        webView.setWebViewClient(WebViewClient())
        //webView.loadUrl("https://estradamovil.constellation-ionc.com/")
        webView.loadUrl("https:google.com/")

    }

    //FUNCION - Para configurar el comportamiento del boton Back
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack() //Ir para atras
        } else {
            super.onBackPressed() //Te saca de la app
        }
    }



        }
*/

class MainActivity : AppCompatActivity() {

    // Private

    private val BASE_URL = "https://estradamovil.constellation-ionc.com/"
   // private val SEARCH_PATH = "/search?q="

    private val tiempo2:Long = 0
    private val tiempoEspera:Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Nuevas - variables
        /*val con2 = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val eswifi2 = con2.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.isConnectedOrConnecting
        val esmovil2 = con2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.isConnectedOrConnecting

        Handler().postDelayed({

            if (!eswifi2 && !esmovil2) {
                Toast.makeText(applicationContext, "No tienes internet", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity2::class.java))
                finish()

            } else {
                Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this, MainActivity::class.java))
                //finish()
            }
        },tiempo2)*/

        // Accion de refresh cuando hace el swipe manual
        /*swipeRefresh.setOnRefreshListener {
            //webView.reload()
            swipeRefresh.isRefreshing = false
        }*/



        // WebView

        webView.webChromeClient = object : WebChromeClient() {

        }

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            //mientras carga
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                //searchView.setQuery(url, false)

                //swipeRefresh.isRefreshing = true

                Toast.makeText(applicationContext, "Actualizando... Por favor espere", Toast.LENGTH_SHORT).show()
                tiempoEspera

            }

            //Cuando ya cargó
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                //swipeRefresh.isRefreshing = false

                val con2 = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                val eswifi2 = con2.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.isConnectedOrConnecting
                val esmovil2 = con2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.isConnectedOrConnecting

                Handler().postDelayed({

                    if (!eswifi2 && !esmovil2) {
                        Toast.makeText(applicationContext, "Revise su conexiòn a internet", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, MainActivity2::class.java))
                        //startActivity(Intent(this, MainActivity2::class.java))
                        finish()

                    } else {
                        Toast.makeText(applicationContext, "Conectado y actualizado con los registros más recientes", Toast.LENGTH_SHORT).show()
                        //startActivity(Intent(this, MainActivity::class.java))
                        //finish()
                    }
                },tiempo2)

            }

        }

        val settings = webView.settings
        settings.javaScriptEnabled = true

        webView.loadUrl(BASE_URL)

    }

    override fun onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}



