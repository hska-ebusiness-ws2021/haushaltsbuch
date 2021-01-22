package com.example.haushaltsbuch.data.model.finances

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/*
* Categories Table
*
* */

object Categories : Table() {
    val name: Column<String> = varchar("name", 30)
    override val primaryKey = PrimaryKey(name, name = "PK_Name_ID")
}

class Category(val name: String) {}