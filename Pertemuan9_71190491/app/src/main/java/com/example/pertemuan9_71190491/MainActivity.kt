package com.example.pertemuan9_71190491

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref: SharedPreferences = getSharedPreferences("databaseku", Context.MODE_PRIVATE)
        val sharedPrefEdit = sharedPref.edit()

        val isLogin = sharedPref.getBoolean("isLogin", false)
        if(isLogin == true){
            setContentView(R.layout.activity_home)

            val spinBahasa = findViewById<Spinner>(R.id.spinBahasa)
            val adapterBahasa = ArrayAdapter.createFromResource(this,R.array.list_bahasa,R.layout.support_simple_spinner_dropdown_item)
            spinBahasa.adapter = adapterBahasa
            spinBahasa.setSelection(sharedPref.getInt("bahasa",1))
            spinBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    sharedPrefEdit.putInt("bahasa", position)
                    sharedPrefEdit.commit()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    TODO("Not yet implemented")
                }
            }
            val spinUkuran= findViewById<Spinner>(R.id.spinUkuran)
            val adapterUkuran = ArrayAdapter.createFromResource(this,R.array.list_ukuran,R.layout.support_simple_spinner_dropdown_item)
            spinUkuran.adapter = adapterUkuran
            spinUkuran.setSelection(sharedPref.getInt("ukuran",1))
            spinUkuran.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    sharedPrefEdit.putInt("ukuran", position)
                    sharedPrefEdit.commit()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    TODO("Not yet implemented")
                }
            }

        }
        else{
            setContentView(R.layout.activity_main)
            val etUsername = findViewById<EditText>(R.id.etUsername)
            val etPassword = findViewById<EditText>(R.id.etPassword)
            val btnLogin = findViewById<Button>(R.id.btnLogin)

            btnLogin.setOnClickListener{
                if(etUsername != null && etPassword != null){
                    sharedPrefEdit.putBoolean("isLogin", true)
                    sharedPrefEdit.commit()

                    val i = Intent(this,MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }

    }

}