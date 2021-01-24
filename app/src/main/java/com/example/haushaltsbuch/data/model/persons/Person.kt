package com.example.haushaltsbuch.data.model.persons

import java.util.UUID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import com.example.haushaltsbuch.data.model.persons.User

/*
* Persons Table
*
* */

object Persons : Table() {
    var id: Column<UUID> = uuid("id")
    var email: Column<String> = varchar("email", 40)
    var firstname: Column<String> = varchar("firstname", 20)
    var lastname: Column<String> = varchar("lastname", 20)
    var username = reference("username", Users.username)
    var password = reference("password", Users.password)
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