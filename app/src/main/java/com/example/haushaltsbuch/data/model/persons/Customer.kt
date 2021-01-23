package com.example.haushaltsbuch.data.model.persons

import com.example.haushaltsbuch.data.model.gamification.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date
import org.joda.time.DateTime

/*
* Customers Table with database scheme
*
* */

object Customers : Table() {
    val id: Column<Int> = reference("id", Persons.id)
    val email: Column<String> = reference("email", Persons.email).references(Users.username)
    val firstname: Column<String> = reference("firstname", Persons.firstname)
    val lastname: Column<String> = reference("lastname", Persons.lastname)

    val backupInfo: Column<String> = varchar("backupInfo", 50)
    val dateOfBirth: Column<DateTime> = date("date")
    val subscription: Column<Int> = reference("subscription", SubscriptionModels.id)
    val achievements: Column<Int> = reference("achievement", Achievements.id)
}

class Customer(
    id: PersonId,
    email: String,
    firstname: String,
    lastname: String,
    user: User,
    val backupInfo: String,
    val dateOfBirth: DateTime,
    val subscription: SubscriptionModel,
    val achievements: MutableList<Achievement>
) : Person(id, email, firstname, lastname, user) {}