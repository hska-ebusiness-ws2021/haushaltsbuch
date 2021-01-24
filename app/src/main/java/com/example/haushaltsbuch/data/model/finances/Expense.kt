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
    var id: Column<UUID> = uuid("id")
    var amount: Column<BigDecimal> = decimal("amount", 8, 2)
    var points: Column<Int> = integer("points")
    var category: Column<String> = reference("category", Categories.name)
    var person: Column<UUID> = reference("person", Persons.id)
    var date: Column<DateTime> = date("date")
    override var primaryKey = PrimaryKey(id, name = "PK_Expense_ID")
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