package com.example.haushaltsbuch.ui.addeditexpense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.haushaltsbuch.R


class CategoryAdapter(private val dataSet: ArrayList<TextWithAmount>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCategory: TextView
        val textViewAmount: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textViewCategory = view.findViewById(R.id.textViewCategory)
            textViewAmount = view.findViewById(R.id.textViewAmount)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.content_expense_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewCategory.text = dataSet[position].category
        viewHolder.textViewAmount.text = dataSet[position].amount.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}