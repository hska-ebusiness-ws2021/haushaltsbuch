package com.example.haushaltsbuch.ui.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.haushaltsbuch.R
import com.example.haushaltsbuch.data.model.finances.Expense
import kotlin.random.Random


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 */
class MyExpenseRecyclerViewAdapter(
    private val values: List<Expense>
) : RecyclerView.Adapter<MyExpenseRecyclerViewAdapter.ViewHolder>() {

    val names = listOf<String>("Essen", "Technik", "Games")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = names[Random.nextInt(0,2)]
        holder.contentView.text = "${item.amount} EUR"
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}