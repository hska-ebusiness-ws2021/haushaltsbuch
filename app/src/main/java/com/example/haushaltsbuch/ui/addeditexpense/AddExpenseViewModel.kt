package com.example.haushaltsbuch.ui.addeditexpense

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

/*
ViewModel to synchronise the date in the activity
 */
class AddExpenseViewModel : ViewModel() {
    val date: MutableLiveData<Date> = MutableLiveData(Date())
    val expenseCategorieList: ArrayList<TextWithAmount> = ArrayList()

    fun setDate(date: Date) {
        this.date.value = date
    }

    fun addExpenseCategorieEntry(twa: TextWithAmount){
        expenseCategorieList.add(twa)
    }
}