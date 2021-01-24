package com.example.haushaltsbuch.data.model.persons

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal
import java.util.UUID

/*
* SubscriptionModels Table
*
* */

object SubscriptionModels : Table() {
    var id: Column<UUID> = uuid("id")
    var name: Column<String> = varchar("name", 20)
    var price: Column<BigDecimal> = decimal("price", 8, 2)
    var billingIntervar: Column<BillingIntervar> = customEnumeration(
        "intervar",
        "enum",
        { value -> BillingIntervar.valueOf(value as String) },
        { it.name }
    )
    override var primaryKey = PrimaryKey(id, name = "PK_Sub_ID")
}

data class SubscriptionModel(
    var id: SubID,
    var name: String,
    var price: BigDecimal,
    var billingIntervar: BillingIntervar
) {}

typealias SubID = UUID