package com.example.haushaltsbuch.ui.addeditexpense

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AddExpenseViewModel : ViewModel() {
    val date: MutableLiveData<Date> = MutableLiveData(Date())

    fun setDate(date: Date) {
        this.date.value = date
    }
}