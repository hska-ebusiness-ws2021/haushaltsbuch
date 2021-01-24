package com.example.haushaltsbuch.data.model.gamification

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.UUID

/*
* Achievements Table
*
* */

object Achievements : Table() {
    var id: Column<UUID> = uuid("id")
    var name: Column<String> = varchar("name", 30)
    var description: Column<String> = varchar("description", 255)
    var isAchieved: Column<Boolean> = bool("false")
    override var primaryKey = PrimaryKey(id, name = "PK_Achieve_ID")
}

data class Achievement(
    var id: AchievementId,
    var name: String,
    var description: String,
    var isAchieved: Boolean
) {}

typealias AchievementId = UUID