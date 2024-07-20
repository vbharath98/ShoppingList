package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]

        holder.tvName.text = currentItem.name
        holder.tvAmount.text = "${currentItem.amount}"

        holder.ivDelete.setOnClickListener {
            viewModel.delete(currentItem)
        }

        holder.ivMinus.setOnClickListener {
            if(currentItem.amount > 0) {
                currentItem.amount--
                viewModel.upsert(currentItem)
            }
        }

        holder.ivPlus.setOnClickListener {
            currentItem.amount++
            viewModel.upsert(currentItem)
        }
    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val ivDelete: ImageView = itemView.findViewById(R.id.iv_delete)
        val ivMinus: ImageView = itemView.findViewById(R.id.iv_minus)
        val ivPlus: ImageView = itemView.findViewById(R.id.iv_plus)
    }

}