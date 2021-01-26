package com.example.haushaltsbuch.data.model.persons

/*
* Enum class for two different billing intervals: monthly and yearly
*
* */

enum class BillingInterval(var value: String) {
    MONTHLY("m"),
    YEARLY("y");
}