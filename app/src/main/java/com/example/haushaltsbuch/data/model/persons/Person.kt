package com.example.haushaltsbuch.data.model.persons

import java.util.*

open class Person(
        val id: PersonId,
        val email: String,
        val firstname: String,
        val lastname: String,
        val user: User
) {}

typealias PersonId = UUID