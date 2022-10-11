package com.example.labestrada

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        setTheme(R.style.Theme_LabEstrada)

        val con = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val eswifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.isConnectedOrConnecting
        val esmovil = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.isConnectedOrConnecting

        if (!eswifi && !esmovil) {
            Toast.makeText(applicationContext, "Desconectado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@PrincipalActivity, MainActivity2::class.java))
            finish()

        } else {
            Toast.makeText(applicationContext, "Conectado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@PrincipalActivity, MainActivity::class.java))
            finish()
        }

    }
}