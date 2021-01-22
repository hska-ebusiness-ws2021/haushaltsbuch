package com.example.haushaltsbuch.data.model.persons

import java.util.UUID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/*
* Persons Table
*
* */

object Persons : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val email: Column<String> = varchar("email", 40)
    val firstname: Column<String> = varchar("firstname", 20)
    val lastname: Column<String> = varchar("lastname", 20)
    override val primaryKey = PrimaryKey(id, name = "PK_Person_ID")
}

open class Person(
    val id: PersonId,
    val email: String,
    val firstname: String,
    val lastname: String,
    val user: User
) {}

typealias PersonId = UUID