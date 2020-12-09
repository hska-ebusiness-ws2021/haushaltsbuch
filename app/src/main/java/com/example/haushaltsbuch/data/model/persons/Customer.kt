package com.example.haushaltsbuch.data.model.persons

import com.example.haushaltsbuch.data.model.gamification.Achievement
import java.util.*

class Customer(
        id: PersonId,
        email: String,
        firstname: String,
        lastname: String,
        address: Address,
        user: User,
        val backupInfo: String,
        val dateOfBirth: Date,
        val subscription: SubscriptionModel,
        val friend: Friend,
        val achievements: List<Achievement>
        ) : Person(
        id, email, firstname, lastname, address, user
                ){}