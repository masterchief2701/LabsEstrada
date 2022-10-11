package com.example.labestrada

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class Launcher : AppCompatActivity() {

    private val tiempo:Long = 2000

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        /*Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, tiempo)*/

            val con = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val eswifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.isConnectedOrConnecting
            val esmovil = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.isConnectedOrConnecting

            Handler().postDelayed({

                if (!eswifi && !esmovil) {
                    Toast.makeText(applicationContext, "No tiene internet", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Launcher, MainActivity2::class.java))
                    finish()

                } else {
                    Toast.makeText(applicationContext, "Conectando al sistema central, por favor espere...", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Launcher, MainActivity::class.java))
                    finish()
                }
         }, tiempo)

    }

}