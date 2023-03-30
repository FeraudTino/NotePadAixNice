package com.example.notepadaixnice.utils

import android.content.Context
import android.text.TextUtils
import com.example.notepadaixnice.Note
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.UUID

private val TAG ="Storage"

fun persiteNote(context: Context, note:Note) {

    if(TextUtils.isEmpty(note.fileName)) {
        note. fileName = UUID.randomUUID().toString()+ ".note"
    }

    val fileOutput = context.openFileOutput(note.fileName, Context.MODE_PRIVATE)
    val outputStream = ObjectOutputStream(fileOutput)

    outputStream.writeObject(note)
    outputStream.close()
}

private fun loadNote(context: Context, filename: String) : Note {
    val fileInput = context.openFileInput(filename)
    val inputStream = ObjectInputStream(fileInput)
    val note = inputStream.readObject() as Note
    inputStream.close()
    return note
}

fun loadNotes(context: Context) : MutableList<Note> {
    val notes = mutableListOf<Note>()
    val notesDir = context.filesDir

    for(filename in notesDir.list()!!) {
        val note = loadNote( context, filename)
        notes.add(note)
    }
    return notes
}


fun deleteNote(context: Context, note: Note) {
    context.deleteFile(note.fileName)
}