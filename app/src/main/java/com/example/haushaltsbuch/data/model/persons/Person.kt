package com.example.haushaltsbuch.data.model.persons

import java.util.UUID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/*
* Persons table and class
*
* */

object Persons : Table() {
    var id: Column<UUID> = uuid("id")
    var email: Column<String> = varchar("email", 255)
    var firstname: Column<String> = varchar("firstname", 255)
    var lastname: Column<String> = varchar("lastname", 255)
    var username: Column<String> = reference("username", Users.username)
    var password: Column<String> = reference("password", Users.password)
    override var primaryKey = PrimaryKey(id, name = "PK_Person_ID")
}

open class Person(
    open var id: PersonId,
    open var email: String,
    open var firstname: String,
    open var lastname: String,
    open var user: User
) {}

typealias PersonId = UUID