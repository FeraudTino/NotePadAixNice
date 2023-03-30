package com.example.notepadaixnice

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class ConfirmDeleteNoteFragment(val noteTitle: String = "" ) : DialogFragment() {

    interface  ConfirmDeleteDialogueListner {
        fun onDialoguePositiveClick()
        fun onDialogueNegativeClick()
    }

    var listner: ConfirmDeleteDialogueListner? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = activity?.let{AlertDialog.Builder(it)}
        builder?.setMessage("Etes vous sure de vouloir suprrimer la note \"$noteTitle\" ?")
            ?.setPositiveButton("Supprimer", DialogInterface.OnClickListener{dialog, id -> listner?.onDialoguePositiveClick() })
            ?.setNegativeButton("Annuler", DialogInterface.OnClickListener{dialog, id -> listner?.onDialogueNegativeClick() })
        return builder!!.create()
    }

}