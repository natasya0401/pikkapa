package com.pikkapa.tools.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import com.pikkapa.R
import com.pikkapa.databinding.DialogConfirmationBinding
import com.pikkapa.domain.TutorialEntity

class DialogBase(
    context: Context,
    val dialog: String,
    val saveText: String,
    val cancelText: String,
    val listenerSave: (Dialog) -> Unit
) : Dialog(context) {

    private lateinit var tvDialog:TextView
    private lateinit var btSave:TextView
    private lateinit var btCancel:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.dialog_confirmation)

        tvDialog = findViewById(R.id.tv_dialog)
        btSave = findViewById(R.id.bt_dialog_submit)
        btCancel = findViewById(R.id.bt_dialog_cancel)

        tvDialog.text = dialog
        btSave.text = saveText
        btCancel.text = cancelText

        btSave.setOnClickListener {
            listenerSave(this)
        }
        btCancel.setOnClickListener {this.dismiss()}
    }

//    override fun create() {
//        super.create()
//
//    }

}