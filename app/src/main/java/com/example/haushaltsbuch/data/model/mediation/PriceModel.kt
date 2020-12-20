package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.BillingInterval
import java.math.BigDecimal
import java.util.*

class PriceModel(
        val price: BigDecimal,
        val isSubscription: Boolean,
        val interval: BillingInterval?,
        ) {
}

