package com.example.haushaltsbuch.ui.addeditexpense

import android.app.Activity
import android.os.Bundle
import android.view.View.GONE
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.haushaltsbuch.R
import com.example.haushaltsbuch.data.model.finances.Category
import com.example.haushaltsbuch.data.model.finances.Expense
import com.example.haushaltsbuch.database.DBHelper
import com.example.haushaltsbuch.databinding.ContentAddEditExpenseBinding
import com.example.haushaltsbuch.ui.DatePickerFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import org.joda.time.DateTime
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class AddEditExpense : AppCompatActivity() {
    private val model: AddExpenseViewModel by viewModels()

    companion object {
        const val IS_EINNAHMEN = "isEinnahmen"
    }


    val parties = arrayOf(
        "Lebensmittel",
        "Haushalt",
        "Miete",
        "Transport",
        "Ausgehen",
        "Kleidung",
        "Studium",
        "Urlaub"
    )
    var i = 0.7

    private lateinit var binding: ContentAddEditExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentAddEditExpenseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //val saveButton = binding.buttonSave

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
            model.addExpenseCategorieEntry(
                TextWithAmount(
                    category.selectedItem.toString(),
                    amount.text.toString().toDouble()
                )
            )
            Toast.makeText(this, "add!", Toast.LENGTH_SHORT).show()
            expenseAdapter.notifyDataSetChanged()
        }
        val date = findViewById<MaterialTextView>(R.id.textDate)
        val saveButton = findViewById<Button>(R.id.button_save)

        saveButton.setOnClickListener {
            val amount = BigDecimal(amount.text.toString())
            val id = UUID.randomUUID()
            val category = category.selectedItem.toString()
            val date = DateTime(model.date.value)
            val dbHelper = DBHelper()
            dbHelper.addExpense(Expense(id, amount, Category(category), date))
            Toast.makeText(this, "Save!", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK);
        }

        val spinner: Spinner = findViewById(R.id.tags_spinner)
        val arrayTags = ArrayAdapter(this, android.R.layout.simple_spinner_item, parties)
        spinner.adapter = arrayTags

        date.setOnClickListener {
            val newFragment = DatePickerFragment(model.date)
            newFragment.show(supportFragmentManager, "dataPicker")
        }

        val title = findViewById<TextView>(R.id.titleExpenses)

        val isEinnahme: Boolean = intent.getBooleanExtra(IS_EINNAHMEN, false)
        if (isEinnahme) {
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