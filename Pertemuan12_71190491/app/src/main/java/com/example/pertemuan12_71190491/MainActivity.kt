package com.example.pertemuan12_71190491

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtKota = findViewById<EditText>(R.id.edtKota)
        val btnCek = findViewById<Button>(R.id.btnCek)

        btnCek.setOnClickListener {
            cekCuaca(edtKota.text.toString())
        }
    }

    fun cekCuaca(kota: String){
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?q=${kota}&appid=70e62b90d6c4178e0c326e2de734fadb"
        val request = StringRequest(Request.Method.GET,
            url,
            Response.Listener<String> { response ->
                val data = JSONObject(response)
                val cuaca = data.getJSONArray("weather").getJSONObject(0).getString("description")
                val suhu = data.getJSONObject("main").getDouble("temp")
                val txvHasil = findViewById<TextView>(R.id.txvHasil)
                txvHasil.text = "${cuaca} (${suhu-273.15}\u2103)"
            },
            Response.ErrorListener {
                print("Tidak ditemukan")
            })

        queue.add(request)
    }
}