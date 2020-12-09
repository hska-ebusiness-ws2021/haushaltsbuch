package com.example.haushaltsbuch.data.model.gamification

import android.app.Person
import java.util.*

class Coupon(
        val person: Person,
        val code: String,
        val minimumOrderValue: Int,
        val expirationDate: Date) {
}

typealias CouponId = UUID