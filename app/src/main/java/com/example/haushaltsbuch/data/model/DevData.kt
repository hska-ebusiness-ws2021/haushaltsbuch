package com.example.haushaltsbuch.data.model

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.FakerConfig
import io.github.serpro69.kfaker.create

val fakerConfig = FakerConfig.builder().create { locale = "de" }

val faker = Faker(fakerConfig)

class FakeData(){

    var people: MutableList<Person>
    var addresses: MutableList<Address>
    var postleitzahlen: MutableList<Postleitzahl>
    var cities: MutableList<City>
    var street: MutableList<Street>
    var Users: MutableList<User>
    var roles: MutableList<Role>

    var customers: MutableList<Customers>
    var companies: MutableList<Company>
    var subscriptionModel: MutableList<SubscriptionModel>

    var expenses: MutableList<Expense>
    var expenseCategories: MutableList<ExpenseCategory>
    var retailers: MutableList<Retailer>

    var coupon: MutableList<Coupon>
    var achievements: MutableList<Achievement>

    var requests: MutableList<AppRequest>
    var offers: MutableList<Offer>
    var pricemodels: MutableList<PriceModel>

    var systemmessages: MutableList<Systemmessage>
    // transitive? friend hasRole hasAchieved

    fun generateData(){
        people = MutableList<Person>(50, ::generatePerson)
    }

    fun generatePerson(): Person  {
        val name = faker.name.firstName()
        val surname = faker.name.lastName()
        val domain = faker.company.name()
        return Person(
            name = name,
            surname = surname,
            email ="$name.$surname@$domain.de",
    )}

    fun generateAddresses()

}