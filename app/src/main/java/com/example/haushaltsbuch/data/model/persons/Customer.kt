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
    var id: Column<Int> = reference("id", Persons.id)
    var email: Column<String> = reference("email", Persons.email).references(Users.username)
    var firstname: Column<String> = reference("firstname", Persons.firstname)
    var lastname: Column<String> = reference("lastname", Persons.lastname)

    var backupInfo: Column<String> = varchar("backupInfo", 50)
    var dateOfBirth: Column<DateTime> = date("date")
    var subscription: Column<Int> = reference("subscription", SubscriptionModels.id)
    var achievements: Column<Int> = reference("achievement", Achievements.id)
}

class Customer(
    id: PersonId,
    email: String,
    firstname: String,
    lastname: String,
    user: User,
    var backupInfo: String,
    var dateOfBirth: DateTime,
    var subscription: SubscriptionModel,
    var achievements: MutableList<Achievement>
) : Person(id, email, firstname, lastname, user) {}