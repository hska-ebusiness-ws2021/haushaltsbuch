package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.Person
import java.util.*

class Offer(
        val id: OfferId,
        val description: String,
        val priceModel: PriceModel,
        val person: Person){
}

typealias OfferId = UUID