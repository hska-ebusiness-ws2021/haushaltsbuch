package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.BillingInterval
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal
import java.util.UUID

/*
* Price model table and data class
*
* */

object PriceModels : Table() {
    var id: Column<UUID> = uuid("id")
    var price: Column<BigDecimal> = decimal("price", 8, 2)
    var isSubscription: Column<Boolean> = bool("false")
    var billingInterval = enumerationByName("value", 1, BillingInterval::class)
    override var primaryKey = PrimaryKey(id, name = "PK_PriceModel_ID")
}

data class PriceModel(
    var id: PriceModelId,
    var price: BigDecimal,
    var isSubscription: Boolean,
    var intervar: BillingInterval,
) {}

typealias PriceModelId = UUID
