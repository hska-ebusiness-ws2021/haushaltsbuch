package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.Person
import com.example.haushaltsbuch.data.model.persons.Persons
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/*
* Requests Table
*
* */

object Requests : Table() {
    val person: Column<Int> = reference("person_id", Persons.id)
    val offer: Column<Int> = reference("offer_id", Offers.id)
}

class Request(
    val person: Person,
    val offer: Offer
) {}