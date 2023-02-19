package com.example.mafia.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.icu.text.DateTimePatternGenerator.PatternInfo.OK
import android.widget.Button
import com.example.mafia.R
object DialogManager {

    fun showDialog(context: Context, messageId: Int, listener: Listener){
        val builder = AlertDialog.Builder(context)
        var dialog: AlertDialog? = null
        builder.setTitle(R.string.attention)
        builder.setMessage(messageId)
        builder.setIcon(R.drawable.icon)
        builder.setPositiveButton(android.R.string.ok){_,_ ->
            listener.onClick()
            dialog?.dismiss()
        }
        builder.setCancelable(true)
        dialog = builder.create()
        dialog.show()
    }

    interface Listener{
        fun onClick()
    }
}