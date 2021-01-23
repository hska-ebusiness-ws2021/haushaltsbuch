package com.example.haushaltsbuch.data.model.persons

import java.util.UUID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/*
* Persons Table
*
* */

object Persons : Table() {
    var id: Column<UUID> = uuid("id")
    var email: Column<String> = varchar("email", 40)
    var firstname: Column<String> = varchar("firstname", 20)
    var lastname: Column<String> = varchar("lastname", 20)
    var username: Column<String> = varchar("username", 20).references(Users.username)
    var password: Column<String> = Customers.reference("user", Users.password)
    override var primaryKey = PrimaryKey(id, name = "PK_Person_ID")
}

data class Person(
    var id: PersonId,
    var email: String,
    var firstname: String,
    var lastname: String,
    var username: String,
    var password: String
)

typealias PersonId = UUID