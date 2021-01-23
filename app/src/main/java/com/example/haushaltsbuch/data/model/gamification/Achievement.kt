package com.example.haushaltsbuch.data.model.gamification

import java.util.*

class Achievement(
        val id: AchievementId,
        val name: String,
        val description: String,
        val isAchieved: Boolean)
{
}

typealias AchievementId = UUID