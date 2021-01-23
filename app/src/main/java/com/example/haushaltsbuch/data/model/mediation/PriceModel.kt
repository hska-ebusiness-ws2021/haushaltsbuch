package com.example.haushaltsbuch.data.model.mediation

import com.example.haushaltsbuch.data.model.persons.BillingIntervar
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal
import java.util.UUID

/*
* Pricemodels Table
*
* */

object PriceModels : Table() {
    var id: Column<Int> = integer("id").autoIncrement()
    var price: Column<BigDecimal> = decimal("price", 8, 2)
    var isSubscription: Column<Boolean> = bool("false")
    var billingIntervar: Column<BillingIntervar> = customEnumeration(
        "intervar",
        "enum",
        { value -> BillingIntervar.valueOf(value as String) },
        { it.name }
    )
    override var primaryKey = PrimaryKey(id, name = "PK_PriceModel_ID")
}

class PriceModel(
    var id: PriceModelId,
    var price: BigDecimal,
    var isSubscription: Boolean,
    var intervar: BillingIntervar,
) {}

typealias PriceModelId = UUID
