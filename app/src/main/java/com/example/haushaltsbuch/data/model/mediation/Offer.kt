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
    val id: Column<Int> = integer("id").autoIncrement()
    val description: Column<String> = varchar("description", 255)
    val priceModel: Column<String> = varchar("pricemodel", 20)
    val personId: Column<Int> = reference("id", Persons.id)
    override val primaryKey = PrimaryKey(id, name = "PK_Offer_ID")
}

class Offer(
    val id: OfferId,
    val description: String,
    val priceModel: PriceModel,
    val person: Person
) {}

typealias OfferId = UUID