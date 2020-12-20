package com.example.haushaltsbuch.data.model.gamification

import com.example.haushaltsbuch.data.model.persons.Person
import java.math.BigDecimal
import java.util.*

class Coupon(
        val person: Person,
        val code: String,
        val minimumOrderValue: BigDecimal,
        val expirationDate: Date) {
}

typealias CouponId = UUID