package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.Person
import com.example.haushaltsbuch.data.model.persons.Persons
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.UUID

/*
* Offers Table
*
* */

object Offers : Table() {
    var id: Column<UUID> = uuid("id")
    var description: Column<String> = varchar("description", 255)
    var priceModel: Column<String> = varchar("pricemodel", 20)
    var personId: Column<UUID> = reference("id", Persons.id)
    override var primaryKey = PrimaryKey(id, name = "PK_Offer_ID")
}

data class Offer(
    var id: OfferId,
    var description: String,
    var priceModel: PriceModel,
    var person: Person
) {}

typealias OfferId = UUID