package com.example.finalproject_71190491


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class SongAdapter (var listSong: ArrayList<Album>, var context: Context): RecyclerView.Adapter<SongAdapter.SongHolder>(), Filterable {
    var firestore: FirebaseFirestore? = null

    val mainList = listSong
    val searchList = ArrayList<Album>(listSong)

    class SongHolder(val view : View): RecyclerView.ViewHolder(view){

        @SuppressLint("RestrictedApi")
        fun bind(lagu: Album, context: Context){
            val firestore = FirebaseFirestore.getInstance()
//            view.findViewById<ImageView>(R.id.foto).setImageResource(contact.foto)

            view.findViewById<TextView>(R.id.judulLagu).setText(lagu.judul)
            view.findViewById<TextView>(R.id.namaPenyanyi).setText(lagu.penyanyi)
            view.setOnClickListener{
                val i: Intent = Intent(view.context, DetailLagu::class.java)
//                i.putExtra("foto",lagu.foto)
                i.putExtra("judul",lagu.judul)
                i.putExtra("penyanyi",lagu.penyanyi)
                i.putExtra("album",lagu.album)
                i.putExtra("genre",lagu.genre)
                i.putExtra("tanggal",lagu.tanggal)
                context.startActivity(i)
            }
            val buttonUpdate = view.findViewById<Button>(R.id.buttonUpdate)
            buttonUpdate.setOnClickListener {
                val i: Intent = Intent(view.context, Update::class.java)
//                i.putExtra("foto",lagu.foto)
                i.putExtra("judul",lagu.judul)
                i.putExtra("penyanyi",lagu.penyanyi)
                i.putExtra("album",lagu.album)
                i.putExtra("genre",lagu.genre)
                i.putExtra("tanggal",lagu.tanggal)
                context.startActivity(i)
            }
            val buttonHapus = view.findViewById<Button>(R.id.buttonDelete)
            buttonHapus.setOnClickListener {
                firestore?.collection(lagu.akun)
                    ?.document(lagu.judul)?.delete()
                    ?.addOnSuccessListener {
                        val i: Intent = Intent(view.context, Login::class.java)
                        context.startActivity(i)
                        getActivity(view.context)?.finish()
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragmentlagu, parent, false)
        return SongHolder(v)
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(listSong[position],context)
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredList = ArrayList<Album>()
                if (constraint.isBlank() or constraint.isEmpty()){
                    filteredList.addAll(searchList)
                }
                else{
                    val filterPattern = constraint.toString().toLowerCase().trim()
                    searchList.forEach{
                        if (it.judul.toLowerCase(Locale.ROOT).contains(filterPattern)){
                            filteredList.add(it)
                        }
                    }
                }
                val result = FilterResults()
                result.values = filteredList

                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mainList.clear()
                mainList.addAll(results!!.values as List<Album>)
                notifyDataSetChanged()
            }
        }
    }
}