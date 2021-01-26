package com.example.haushaltsbuch.ui.goals

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.haushaltsbuch.R
import com.example.haushaltsbuch.ui.DatePickerFragment
import com.example.haushaltsbuch.ui.home.HomeActivity
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat

/*
 Goal activity to define new goals
 */
class AddGoals : AppCompatActivity() {
    private val model: GoalViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgoal)
        setSupportActionBar(findViewById(R.id.toolbar))
        // ===================================================================
        //                         Link UI elements
        // ===================================================================
        val cancelButton = findViewById<Button>(R.id.button_cancel)
        val backButton = findViewById<Button>(R.id.button_back)
        val saveButton = findViewById<Button>(R.id.button_save)
        val startDateCalendar = findViewById<MaterialTextView>(R.id.startDate)
        val endDateGoals = findViewById<MaterialTextView>(R.id.endDate)
        // ===================================================================

        saveButton.setOnClickListener {
            Toast.makeText(this, "Save!", Toast.LENGTH_SHORT).show()
        }
        cancelButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // Show the data picker to select a date
        startDateCalendar.setOnClickListener {
            val newFragment = DatePickerFragment(model.startDate)
            newFragment.show(supportFragmentManager, "dataPicker")
        }
        endDateGoals.setOnClickListener {
            val newFragment = DatePickerFragment(model.endDate)
            newFragment.show(supportFragmentManager, "dataPicker")
        }

        // Update start date when it is changed in the dataPicker
        model.startDate.observe(this, Observer {
            val format = SimpleDateFormat("MMM dd.yyyy")
            startDateCalendar.text = format.format(it)
        })

        // Update end date when it is changed in the dataPicker
        model.endDate.observe(this, Observer {
            val format = SimpleDateFormat("MMM dd.yyyy")
            endDateGoals.text = format.format(it)
        })
    }
}