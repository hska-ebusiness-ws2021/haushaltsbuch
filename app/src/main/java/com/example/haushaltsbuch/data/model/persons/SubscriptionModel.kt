package com.example.haushaltsbuch.data.model.persons

import java.math.BigDecimal

class SubscriptionModel (
        val name: String,
        val price: BigDecimal,
        val billingInterval: BillingInterval
        ) {
}