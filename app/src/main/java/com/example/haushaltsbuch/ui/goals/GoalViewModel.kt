package com.example.haushaltsbuch.ui.goals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class GoalViewModel : ViewModel() {
    val startDate: MutableLiveData<Date> = MutableLiveData(Date())
    val endDate: MutableLiveData<Date> = MutableLiveData(Date())

    fun setStartDate(date: Date) {
        this.startDate.value = date
    }
    fun setEndDate(date: Date) {
        this.endDate.value = date
    }
}