package com.example.haushaltsbuch.data.model.persons

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Column

/*
* Users Table
*
* */

object Users : Table() {
    var username: Column<String> = varchar("username", 20)
    var password: Column<String> = varchar("password", 20)
    override val primaryKey = PrimaryKey(username, name = "PK_Username")
}

data class User(
    var username: String,
    var password: String
) {
    override fun toString(): String = "User($username, $password)"
    fun toUser() = User(username, password)
}
