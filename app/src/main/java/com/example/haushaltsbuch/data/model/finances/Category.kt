package com.example.haushaltsbuch.data.model.finances

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/*
* Categories Table
*
* */

object Categories : Table() {
    var name: Column<String> = varchar("name", 30)
    //override var primaryKey = PrimaryKey(name, name = "PK_Name_ID")
}

data class Category(var name: String) {}