package com.example.notepadaixnice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var notes:MutableList<Note>
    lateinit var adapter:NoteAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        notes = mutableListOf<Note>()

        notes.add(Note("Note 1", "Super cela fonctionne parfaitement"))
        notes.add(Note("Memo M2I", "Prochaine formation à Aix et Nice ne sera pas en distanciel"))
        notes.add(Note("Memo Foot", "La France sera la future equipe championne d'Europe devant nos amis Anglais!!!!"))
        notes.add(Note("Pourquoi Kotlin ?", "Parce que c'est la formation mon petit !! "))

        adapter= NoteAdapter(notes,this)

        val recyclerView = findViewById<RecyclerView>(R.id.notes_recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
    override fun onClick(view: View) {
        if (view.tag != null)
            Log.i("NoteListActivity", "Note de ma list")
    }
}