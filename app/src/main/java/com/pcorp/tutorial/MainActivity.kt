package com.pcorp.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var counter = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = mutableListOf<String>("Buku 1", "Buku 2", "Buku 3")
        val adapter = RecyclerViewAdapter(data)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val inputBox = findViewById<EditText>(R.id.input_txt)
        val inputBtn = findViewById<Button>(R.id.input_btn)

        inputBtn.setOnClickListener {
            val judulBuku = inputBox.text.toString()
//            Kondisi jika tidak ada input text
            if (judulBuku.isBlank()){
                Toast.makeText(this, "Judul buku tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
//            Masukkan input ke list
            else{
                data.add(judulBuku)
                adapter.notifyItemInserted(adapter.getItemCount()-1)
                inputBox.text.clear()
            }
        }

    }

    class RecyclerViewAdapter(private val dataSet: MutableList<String>) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView
            init {
                textView = view.findViewById(R.id.judul_buku)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.books_item, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.textView.text = dataSet[position]
        }

        override fun getItemCount() = dataSet.size
    }

    class custom

}