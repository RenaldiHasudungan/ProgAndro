package com.example.pertemuan7_71190491

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_contact_detail)

        val name = getIntent().getStringExtra("name")
        val number = getIntent().getStringExtra("number")
        val image = getIntent().getIntExtra("image",0)
        val address = getIntent().getStringExtra("address")
        val email = getIntent().getStringExtra("email")

        val namaKontak = findViewById<TextView>(R.id.tvName_detail)
        namaKontak.text = "Nama: ${name}"

        val nomorKontak = findViewById<TextView>(R.id.tvNumber_detail)
        nomorKontak.text = "Nomor: ${number}"

        val gambarKontak = findViewById<ImageView>(R.id.ivImage_detail).setImageResource(image)

        val alamatKontak = findViewById<TextView>(R.id.tvAddress_detail)
        alamatKontak.text = "Alamat: ${address}"

        val emailKontak = findViewById<TextView>(R.id.tvEmail_detail)
        emailKontak.text = "Email: ${email}"

    }

}