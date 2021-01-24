package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.Person
import com.example.haushaltsbuch.data.model.persons.Persons
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.UUID

/*
* Requests Table
*
* */

object Requests : Table() {
    var person: Column<UUID> = reference("person_id", Persons.id)
    var offer: Column<UUID> = reference("offer_id", Offers.id)
}

data class Request(
    var person: Person,
    var offer: Offer
) {}