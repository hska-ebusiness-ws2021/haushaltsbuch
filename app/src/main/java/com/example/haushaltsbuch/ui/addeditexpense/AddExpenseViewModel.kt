package com.example.haushaltsbuch.ui.addeditexpense

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList


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