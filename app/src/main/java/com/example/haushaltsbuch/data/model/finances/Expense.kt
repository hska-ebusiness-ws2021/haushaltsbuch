package com.example.haushaltsbuch.data.model.finances

import com.example.haushaltsbuch.data.model.persons.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal
import java.util.UUID
import org.jetbrains.exposed.sql.jodatime.date
import org.joda.time.DateTime

/*
* Expenses Table
*
* */

object Expenses : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val amount: Column<BigDecimal> = decimal("amount", 8, 2)
    val points: Column<Int> = integer("points")
    val category: Column<String> = reference("category", Categories.name)
    val person: Column<Int> = reference("person_id", Persons.id)
    val date: Column<DateTime> = date("date")
    override val primaryKey = PrimaryKey(id, name = "PK_Expense_ID")
}

data class Expense(
    var id: ExpenseId,
    var amount: BigDecimal,
    var points: Int,
    var category: Category,
    var person: Person,
    var date: DateTime
) {}

typealias ExpenseId = UUID