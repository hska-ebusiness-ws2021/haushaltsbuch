package com.example.haushaltsbuch.database

import com.example.haushaltsbuch.data.model.persons.*
import com.example.haushaltsbuch.data.model.finances.*
import com.example.haushaltsbuch.data.model.mediation.*
import com.example.haushaltsbuch.data.model.gamification.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import android.widget.TextView
import android.view.View
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class Main() {

    /*
    * Methods for database activities from database helper
    * */

    val db = Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC", "admin", "secret")

    private fun createTables() = transaction(db) {
        SchemaUtils.create(Users, Persons)
    }
}

