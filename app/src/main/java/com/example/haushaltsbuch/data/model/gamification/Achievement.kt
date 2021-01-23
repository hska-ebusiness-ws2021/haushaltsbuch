package com.example.haushaltsbuch.data.model.gamification

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.UUID

/*
* Achievements Table
*
* */

object Achievements : Table() {
        val id: Column<Int> = integer("id").autoIncrement()
        val name: Column<String> = varchar("name", 30)
        val description: Column<String> = varchar("description", 255)
        val isAchieved: Column<Boolean> = bool("false")
        override val primaryKey = PrimaryKey(id, name = "PK_Achieve_ID")
}

class Achievement(
        val id: AchievementId,
        val name: String,
        val description: String,
        val isAchieved: Boolean
) {}

typealias AchievementId = UUID