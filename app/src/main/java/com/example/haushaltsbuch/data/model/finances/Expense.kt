package com.example.haushaltsbuch.data.model.finances

import com.example.haushaltsbuch.data.model.persons.Person
import java.util.*

data class Expense (
        val id: ExpenseId,
        val amount: Int,
        val merchant: Merchant,
        val points: Int,
        val category: Category,
        val person: Person
        ) {

}

typealias ExpenseId = UUID