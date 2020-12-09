package com.example.haushaltsbuch.data.model.persons

class User(
        val username: String,
        val password: String,
        val role: List<Role>
) {
}