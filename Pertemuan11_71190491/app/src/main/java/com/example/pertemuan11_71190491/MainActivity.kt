package com.example.pertemuan11_71190491

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nama = findViewById<EditText>(R.id.etNama)
        val nim = findViewById<EditText>(R.id.etNIM)
        val ipk = findViewById<EditText>(R.id.etIPK)

        val btnsimpan = findViewById<Button>(R.id.btnSimpan)
        val btncari = findViewById<Button>(R.id.btnCari)

        val hasilNama = findViewById<TextView>(R.id.tvHasilNama)
        val hasilNIM = findViewById<TextView>(R.id.tvHasilNIM)
        val hasilIPK = findViewById<TextView>(R.id.tvHasilIPK)


        val firestore = FirebaseFirestore.getInstance()

        btnsimpan.setOnClickListener{
            if(nama.text.toString() != "" && nim.text.toString() != "" && ipk.text.toString() != ""){

                val mahasiswa = Mahasiswa(nama.text.toString(), nim.text.toString(), ipk.text.toString())
                firestore.collection("Mahasiswa").add(mahasiswa)

                Toast.makeText(this,"Data berhasil ditambahkan", Toast.LENGTH_LONG).show()
                nama.setText("")
                nim.setText("")
                ipk.setText("")

            }else{
                Toast.makeText(this,"Data gagal ditambahkan", Toast.LENGTH_LONG).show()
            }
        }

        btncari.setOnClickListener{
            if(nama.text.toString() != ""){

                val mahasiswa = Mahasiswa(nama.text.toString(), nim.text.toString(), ipk.text.toString())

                firestore.collection("Mahasiswa").whereEqualTo("nama", nama.text.toString()).get().addOnSuccessListener {
                        hasilPencarian->
                    for (hasil in hasilPencarian){
                        hasilNama.setText("${hasil.data["nama"]}")
                        hasilNIM.setText("${hasil.data["nim"]}")
                        hasilIPK.setText("${hasil.data["ipk"]}")
                    }
                }

            }else{
                Toast.makeText(this,"Data tidak ditemukan", Toast.LENGTH_LONG).show()
            }
        }

    }




}
