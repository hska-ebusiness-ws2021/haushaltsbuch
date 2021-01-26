package com.example.haushaltsbuch.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import java.util.*

class DatePickerFragment() : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var date: MutableLiveData<Date>

    constructor(dateData: MutableLiveData<Date>) : this(){
        date = dateData
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(this.requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        date.value = Date(year - 1900 ,month,day)
    }

}