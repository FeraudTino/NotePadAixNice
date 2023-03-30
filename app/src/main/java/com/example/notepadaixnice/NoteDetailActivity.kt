package com.example.notepadaixnice


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class NoteDetailActivity : AppCompatActivity() {

    companion object {
        val REQUEST_EDIT_NOTE = 1
        val EXTRA_NOTE = "note"
        val EXTRA_NOTE_INDEX = "noteIndex"
    }

    lateinit var note: Note
    var noteIndex: Int = -1

    lateinit var titleView: TextView
    lateinit var textView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        note = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
          intent.getParcelableExtra(EXTRA_NOTE, Note::class.java)!!
        }
        else{
            intent.getParcelableExtra<Note>(EXTRA_NOTE)!!
        }

        noteIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)

        titleView = findViewById<TextView>(R.id.title_view_detail)
        textView = findViewById<TextView>(R.id.text_view_detail)

        titleView.text = note.title
        textView.text = note.text
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_note_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                saveNote()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun saveNote() {
        note.title = titleView.text.toString()
        note.text = textView.text.toString()
        intent = Intent()
        intent.putExtra(EXTRA_NOTE, note)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}