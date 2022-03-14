package com.example.pertemuan5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("user")
        val txvHello = findViewById<TextView>(R.id.txvHello)
        txvHello.text = "Selamat Datang ${username} !!!"

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener{
            val i = Intent (this, LoginActivity::class.java)
            startActivity(i)
        }
    }
}