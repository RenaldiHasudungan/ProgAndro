package com.example.pertemuan7_71190491

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //siapkan data dummy
        val listContact = ArrayList<Contact>()
        listContact.add(Contact("Bill Gate", "(206) 709-3400", R.mipmap.billgates,"Medina, Washington, Amerika Serikat","media@gatesfoundation.org"))
        listContact.add(Contact("Deddy Corbuzier", "+6281211459958", R.mipmap.deddy,"Bintaro, Tangerang Selatan, Indonesia","corbuzier@ocd.com"))
        listContact.add(Contact("Elon Musk", "1956-465-6846",  R.mipmap.elonmusk,"Bel Air, Los Angeles, California, Amerika Serikat","elonmusk@tesla.com"))

        //siapkan RecyclerView
        val rvContact = findViewById<RecyclerView>(R.id.rvListContact)
        rvContact.layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(listContact)
        rvContact.adapter = adapter


    }
}