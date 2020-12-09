package com.example.haushaltsbuch.data.model

import com.example.haushaltsbuch.data.model.finances.Category
import com.example.haushaltsbuch.data.model.finances.Expense
import com.example.haushaltsbuch.data.model.finances.Merchant
import com.example.haushaltsbuch.data.model.gamification.Achievement
import com.example.haushaltsbuch.data.model.gamification.Coupon
import com.example.haushaltsbuch.data.model.mediation.Offer
import com.example.haushaltsbuch.data.model.mediation.PriceModel
import com.example.haushaltsbuch.data.model.mediation.Request
import com.example.haushaltsbuch.data.model.persons.*
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.FakerConfig
import io.github.serpro69.kfaker.create
import java.util.*
import kotlin.random.Random

val fakerConfig = FakerConfig.builder().create { locale = "de" }

val faker = Faker(fakerConfig)

class FakeData(){

    var people: MutableList<Person>? = null
    var addresses: MutableList<Address>? = null
    var postCode: MutableList<PostCode>? = null
    var street: MutableList<Street>? = null
    var users: MutableList<User>? = null
    var roles: MutableList<MutableList<Role>>? = null
    var friends: MutableList<Friend>? = null
    var locations: MutableList<Location>? = null

    var customers: MutableList<Customer>? = null
    var companies: MutableList<Company>? = null
    var subscriptionModel: MutableList<SubscriptionModel>? = null

    var expenses: MutableList<Expense>? = null
    var expenseCategories: MutableList<Category>? = null
    var retailers: MutableList<Merchant>? = null

    var coupon: MutableList<Coupon>? = null
    var achievements: MutableList<Achievement>? = null

    var requests: MutableList<Request>? = null
    var offers: MutableList<Offer>? = null
    var pricemodels: MutableList<PriceModel>? = null

    // transitive? friend hasRole hasAchieved

    val addressCount = 50
    val peopleCount = 50
    val postcodeCount = 5
    val streetcount = 10
    val locationCount = 5

    fun generateData(){
        locations = MutableList<Location>(locationCount, ::generateLocation)
        postCode = MutableList<PostCode>(postcodeCount, ::generatePostCode)
        street = MutableList<Street>(streetcount, ::generateStreet)
        addresses = MutableList<Address>(50, ::generateAddress)
        generateRoles()
        users = MutableList(peopleCount, ::generateUser)
        people = MutableList<Person>(peopleCount, ::generatePerson)

    }

    fun generatePerson(index: Int): Person  {
        val name = faker.name.firstName()
        val surname = faker.name.lastName()
        val domain = faker.company.name()
        return Person(
                id = UUID.randomUUID(),
                firstname = name,
                lastname = surname,
                email ="$name.$surname@$domain.de",
                address = addresses!![index/(peopleCount/addressCount)],
                user = users!![index],

    )}

    fun generateAddress(index: Int): Address {
        return Address(
                postCode = postCode!![index/(addressCount/postcodeCount)],
                street = street!![index/(addressCount/streetcount)],
                person = null,
        )
    }

    fun generatePostCode(index: Int): PostCode {
        return PostCode(
                postCode = faker.address.postcode(),
                location = locations!![index/(postcodeCount/locationCount)],
        )
    }

    fun generateLocation(index: Int): Location {
        return Location(name = faker.address.city())
    }

    fun generateStreet(index: Int): Street {
        return Street(
                name = faker.address.streetName(),
                houseNumber = index.toString(),
                additionalInfo = null,
        )
    }

    fun generateUser(index: Int): User {
        return User(
                username = faker.funnyName.name(),
                password = "password",
                role = roles!![Random.nextInt(0,1)],
        )
    }

    fun generateRoles(){
        roles = mutableListOf(mutableListOf( Role.STANDARD), mutableListOf( Role.PREMIUM))
        return
    }

}