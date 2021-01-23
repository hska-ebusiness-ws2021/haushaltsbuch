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
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 20)
    val price: Column<BigDecimal> = decimal("price", 8, 2)
    val billingInterval: Column<BillingInterval> = customEnumeration(
        "interval",
        "enum",
        { value -> BillingInterval.valueOf(value as String) },
        { it.name }
    )
    override val primaryKey = PrimaryKey(id, name = "PK_Sub_ID")
}

open class SubscriptionModel(
    val id: SubID,
    val name: String,
    val price: BigDecimal,
    val billingInterval: BillingInterval
) {}

typealias SubID = UUID