package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.BillingInterval
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal
import java.util.UUID

/*
* Pricemodels Table
*
* */

object PriceModels : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val price: Column<BigDecimal> = decimal("price", 8, 2)
    val isSubscription: Column<Boolean> = bool("false")
    val billingInterval: Column<BillingInterval> = customEnumeration(
        "interval",
        "enum",
        { value -> BillingInterval.valueOf(value as String) },
        { it.name }
    )
    override val primaryKey = PrimaryKey(id, name = "PK_PriceModel_ID")
}

class PriceModel(
    val id: PriceModelId,
    val price: BigDecimal,
    val isSubscription: Boolean,
    val interval: BillingInterval,
) {}

typealias PriceModelId = UUID
