package com.example.haushaltsbuch.data.model.persons

import java.math.BigDecimal
import java.util.*

class SubscriptionModel (
        val name: String,
        val price: BigDecimal,
        val billingInterval: BillingInterval
        ) {
}