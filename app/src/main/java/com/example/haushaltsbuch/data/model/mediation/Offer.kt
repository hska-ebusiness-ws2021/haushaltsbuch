package com.example.haushaltsbuch.data.model.mediation

import android.app.Person
import java.util.*

class Offer(
        val id: OfferId,
        val description: String,
        val priceModel: PriceModel,
        val person: Person){
}

typealias OfferId = UUID