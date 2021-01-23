package com.example.haushaltsbuch.ui.addeditexpense

import android.os.Bundle
import android.view.View.GONE
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.haushaltsbuch.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat

class AddEditExpense : AppCompatActivity() {
    private val model: AddExpenseViewModel by viewModels()
    companion object {
        const val IS_EINNAHMEN = "isEinnahmen"
    }


    val parties = arrayOf(
        "Lebensmittel", "Haushalt", "Miete", "Transport", "Ausgehen", "Kleidung", "Studium", "Urlaub"
    )
    var i = 0.7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_add_edit_expense)
        setSupportActionBar(findViewById(R.id.toolbar))
        val amount = findViewById<EditText>(R.id.editTextAmount)
        val category = findViewById<Spinner>(R.id.tags_spinner)
        val imageTag = findViewById<AppCompatImageView>(R.id.imageTags)
        val expenseAdapter = CategoryAdapter(model.expenseCategorieList)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = expenseAdapter
        val addButton = findViewById<FloatingActionButton>(R.id.button_add)
        addButton.setOnClickListener {
            model.addExpenseCategorieEntry(TextWithAmount(category.selectedItem.toString(), amount.text.toString().toDouble() ))
            Toast.makeText(this, "add!", Toast.LENGTH_SHORT).show()
            expenseAdapter.notifyDataSetChanged()
        }

        val saveButton = findViewById<Button>(R.id.button_save)

        saveButton.setOnClickListener {
            Toast.makeText(this, "Save!", Toast.LENGTH_SHORT).show()
        }

        val spinner: Spinner = findViewById(R.id.tags_spinner)
        val arrayTags = ArrayAdapter(this, android.R.layout.simple_spinner_item, parties)
        spinner.adapter = arrayTags

        val date = findViewById<MaterialTextView>(R.id.textDate)
        date.setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(supportFragmentManager, "dataPicker")
        }

        val title= findViewById<TextView>(R.id.titleExpenses)

        val isEinnahme : Boolean = intent.getBooleanExtra(IS_EINNAHMEN, false)
        if (isEinnahme){
            category.visibility = GONE
            imageTag.visibility = GONE
            addButton.visibility = GONE
            recyclerView.visibility = GONE
            title.text = "Einnahmen"
        }

        model.date.observe(this, Observer {
            val format = SimpleDateFormat("MMM dd.yyyy")
            date.text = format.format(it)
        })

    }
}

