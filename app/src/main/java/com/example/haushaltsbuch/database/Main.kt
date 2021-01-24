package com.example.haushaltsbuch.database

import com.example.haushaltsbuch.data.model.persons.*
import com.example.haushaltsbuch.data.model.finances.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import android.widget.TextView
import android.view.View
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class Main() {

    /*
    * Create database and tables
    * */
    fun main() {
        Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC", "admin", "secret")

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Users, Persons, Customers, Expenses, Categories)
        }
    }
}

