package com.example.haushaltsbuch.data.model.persons

import com.example.haushaltsbuch.data.model.gamification.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date
import org.joda.time.DateTime
import java.util.UUID

/*
* Customer table and data class
*
* */

object Customers : Table() {
    var id: Column<UUID> = reference("id", Persons.id)
    var email: Column<String> = reference("email", Persons.email)
    var firstname: Column<String> = reference("firstname", Persons.firstname)
    var lastname: Column<String> = reference("lastname", Persons.lastname)
    var username: Column<String> = reference("username", Users.username)
    var password: Column<String> = reference("password", Users.password)
    var backupInfo: Column<String> = varchar("backupInfo", 50)
    var dateOfBirth: Column<DateTime> = date("date")
    var subscription: Column<UUID> = reference("subscription", SubscriptionModels.id)
    var achievements: Column<UUID> = reference("achievement", Achievements.id)
}

data class Customer(
    override var id: PersonId,
    override var email: String,
    override var firstname: String,
    override var lastname: String,
    override var user: User,
    var backupInfo: String,
    var dateOfBirth: DateTime,
    var subscription: SubscriptionModel,
    var achievements: MutableList<Achievement>
) : Person(id, email, firstname, lastname, user) {}