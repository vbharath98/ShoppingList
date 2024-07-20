package com.example.shoppinglist.ui.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.other.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : ComponentActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val rvSHoppingItems: RecyclerView = findViewById(R.id.rv_shopping)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        val viewModel: ShoppingViewModel by viewModels { factory }

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        rvSHoppingItems.layoutManager = LinearLayoutManager(this)
        rvSHoppingItems.adapter = adapter

        viewModel.getALlShoppingItems().observe(this, Observer {
            adapter.items = it
             adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this, object: AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }

            }).show()
        }

    }
}
