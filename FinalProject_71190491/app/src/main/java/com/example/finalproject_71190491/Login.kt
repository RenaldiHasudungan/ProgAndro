package com.example.finalproject_71190491


import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class Login : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    lateinit var searchView: SearchView
    lateinit var listSong: ArrayList<Album>
    lateinit var recyclerView: RecyclerView
    lateinit var itemAdapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val googleClient = GoogleSignIn.getClient(this, signInOptions)
        val acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        val buttonLogOut = findViewById<Button>(R.id.logoutbutton)
        firestore = FirebaseFirestore.getInstance()
        buttonLogOut.setOnClickListener {
            googleClient.signOut().addOnSuccessListener{
                FirebaseAuth.getInstance().signOut()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
        val buttonCreate = findViewById<Button>(R.id.createbutton)
        buttonCreate.setOnClickListener {
            val i = Intent(this, Create::class.java)
            startActivity(i)
        }

        if (acct!=null){
            val personName = acct.email
            if (personName != null) {
                firestore?.collection(personName)
                    ?.get()
                    ?.addOnSuccessListener {documents ->
                        listSong = arrayListOf<Album>()
                        listSong.clear()
                        for (document in documents){
                            listSong.add(Album(
                                document["judul"].toString(),
                                document["penyanyi"].toString(),
                                document["album"].toString(),
                                document["genre"].toString(),
                                document["tanggal"].toString(),
                                document["akun"].toString()
                            ))
                        }


                        var showingSong = findViewById<RecyclerView>(R.id.showingSong)
                        showingSong.layoutManager=LinearLayoutManager(this@Login)
                        val adapter = SongAdapter(listSong, this@Login)
                        showingSong.adapter=adapter
                        searchView = findViewById(R.id.searcher)
                        searchView.clearFocus()
                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(text: String): Boolean {
                                listSong.clear()
                                for (document in documents){
                                    if (document["judul"].toString().toLowerCase().contains(text.toLowerCase())){
                                        listSong.add(Album(
                                            document["judul"].toString(),
                                            document["penyanyi"].toString(),
                                            document["album"].toString(),
                                            document["genre"].toString(),
                                            document["tanggal"].toString(),
                                            document["akun"].toString()
                                        ))
                                    }
                                }


                                var showingSong = findViewById<RecyclerView>(R.id.showingSong)
                                showingSong.layoutManager=LinearLayoutManager(this@Login)
                                val adapter = SongAdapter(listSong, this@Login)
                                showingSong.adapter=adapter
                                return true
                            }
                        })




                    }
            }

        }

    }
}