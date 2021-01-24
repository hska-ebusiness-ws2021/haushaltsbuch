package com.example.haushaltsbuch.ui.goals

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.haushaltsbuch.R
import com.example.haushaltsbuch.ui.DatePickerFragment
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat


class AddGoals : AppCompatActivity() {
    private val model: GoalViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgoal)
        setSupportActionBar(findViewById(R.id.toolbar))
        val amount = findViewById<EditText>(R.id.editTextAmountGoal)


        val saveButtonGoals = findViewById<Button>(R.id.button_save_Goals)

        saveButtonGoals.setOnClickListener {
            Toast.makeText(this, "Save!", Toast.LENGTH_SHORT).show()
        }

        val startDateCalendar = findViewById<MaterialTextView>(R.id.startDate)
        startDateCalendar.setOnClickListener {
            val newFragment = DatePickerFragment(model.startDate)
            newFragment.show(supportFragmentManager, "dataPicker")
        }
        val endDateGoals = findViewById<MaterialTextView>(R.id.endDate)
        endDateGoals.setOnClickListener {
            val newFragment = DatePickerFragment(model.endDate)
            newFragment.show(supportFragmentManager, "dataPicker")
        }
        val title= findViewById<TextView>(R.id.editTextTitleGoals)

        model.startDate.observe(this, Observer {
            val format = SimpleDateFormat("MMM dd.yyyy")
            startDateCalendar.text = format.format(it)
        })

        model.endDate.observe(this, Observer {
            val format = SimpleDateFormat("MMM dd.yyyy")
            endDateGoals.text = format.format(it)
        })
    }
}