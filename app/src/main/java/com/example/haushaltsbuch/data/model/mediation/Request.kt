package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.Person

class Request(
        val person: Person,
        val offer: Offer) {
}