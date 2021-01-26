package com.example.haushaltsbuch.data.model.finances

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/*
* Category table and data class
*
* */

object Categories : Table() {
    var name: Column<String> = varchar("name", 30)
}

data class Category(var name: String) {}