package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.BillingInterval
import java.util.*

class PriceModel(
        val price: Currency,
        val isSubscription: Boolean,
        val interval: BillingInterval
        ) {
}

