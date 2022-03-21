package com.example.pertemuan6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentB: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewB = inflater.inflate(R.layout.fragment_b, container, false)
        val buttonB = viewB.findViewById<Button>(R.id.buttonB)

        buttonB.setOnClickListener {
            val i = Intent(context, Page3Activity::class.java);
            startActivity(i)
            Toast.makeText(context,"Anda sekarang berada di Halaman 3",Toast.LENGTH_SHORT).show()
        }
        return viewB
    }
}
