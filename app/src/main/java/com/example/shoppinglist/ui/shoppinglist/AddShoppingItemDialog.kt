package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem

class AddShoppingItemDialog(context: Context, private val addDialogListener: AddDialogListener): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        val tvAdd: TextView? = findViewById(R.id.tvAdd)
        val tvCancel: TextView? = findViewById(R.id.tvCancel)
        val etName: EditText? = findViewById(R.id.etName)
        val etAmount: EditText? = findViewById(R.id.etAmount)

        tvAdd?.setOnClickListener {
            val name = (etName?.text ?: "").toString()
            val amount = (etAmount?.text ?: "").toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all information", Toast.LENGTH_SHORT).show()
            } else {
                val item = ShoppingItem(name, amount.toInt())
                addDialogListener.onAddButtonClicked(item)
                dismiss()
            }
        }

        tvCancel?.setOnClickListener {
            cancel()
        }
    }
}