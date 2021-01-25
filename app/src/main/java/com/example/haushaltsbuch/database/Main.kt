package com.example.haushaltsbuch.database

import android.util.Log
import com.example.haushaltsbuch.data.model.finances.*
import com.example.haushaltsbuch.data.model.gamification.*
import com.example.haushaltsbuch.data.model.mediation.*
import com.example.haushaltsbuch.data.model.persons.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Database

class Main {
    /*
    * Class to create a database and tables
    * */

    fun main() {
        Database.connect(
            "jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;",
            "org.h2.Driver",
            "admin",
            ""
        )

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(
                Categories,
                Expenses,
                Achievements,
                Offers,
                PriceModels,
                Requests,
                Customers,
                Persons,
                SubscriptionModels,
                Users
            )
        }
        Log.i("DB_CREATE", "Database, Tables created and initialized.")
    }
}