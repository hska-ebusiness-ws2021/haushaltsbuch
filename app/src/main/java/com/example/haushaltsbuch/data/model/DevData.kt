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

class FakeData() {

    // amount of Data
    private val addressCount = 50
    private val peopleCount = 50
    private val postcodeCount = 5
    private val streetcount = 10
    private val locationCount = 5
    private val companyCount = 10
    private val customerCount = peopleCount - companyCount
    private val categoryCount = 10
    private val couponCount = 20
    private val achievementCount = 20
    private val offerCount = 20
    private val requestCount = 40

    val locations = List<Location>(locationCount, ::generateLocation)
    val postCode = MutableList<PostCode>(postcodeCount, ::generatePostCode)
    val street = MutableList<Street>(streetcount, ::generateStreet)
    val addresses = MutableList<Address>(50, ::generateAddress)
    val users = MutableList(peopleCount, ::generateUser)
    val people = MutableList<Person>(peopleCount, ::generatePerson)
    val friends = MutableList<Friend>(peopleCount, ::generateFriend)
    val customers = MutableList<Customer>(customerCount, ::generateCustomer)
    val companies = MutableList<Company>(companyCount, ::generateCompany)
    val expenseCategories = MutableList<Category>(categoryCount, ::generateCategory)
    val expenses = MutableList<MutableList<Expense>>(customerCount, ::generateExpenseList)
    val coupon = MutableList<Coupon>(couponCount, ::generateCoupon)
    val achievements = MutableList<Achievement>(achievementCount, ::generateAchievement)
    val pricemodels = MutableList<PriceModel>(offerCount, ::generatePriceModel)
    val offers = MutableList<Offer>(offerCount, ::generateOffer)
    val requests = MutableList<Request>(requestCount, ::generateRequest)


fun generateTpeople() {

}

fun generatePerson(index: Int): Person {
    val name = faker.name.firstName()
    val surname = faker.name.lastName()
    val domain = faker.company.name()
    return Person(
            id = UUID.randomUUID(),
            firstname = name,
            lastname = surname,
            email = "$name.$surname@$domain.de",
            address = addresses[index / (peopleCount / addressCount)],
            user = users.get(index),

            )
}

fun generateAddress(index: Int): Address {
    return Address(
            postCode = postCode[index / (addressCount / postcodeCount)],
            street = street[index / (addressCount / streetcount)],
            person = null,
    )
}

fun generatePostCode(index: Int): PostCode {
    return PostCode(
            postCode = faker.address.postcode(),
            location = locations[index / (postcodeCount / locationCount)],
    )
}

fun generateLocation(index: Int): Location {
    index
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
    index
    return User(
            username = faker.funnyName.name(),
            password = "password",
            role = roles[Random.nextInt(0, 1)],
    )
}

fun generateRoles() {
    roles = mutableListOf(mutableListOf(Role.STANDARD), mutableListOf(Role.PREMIUM))
    return
}

fun generateFriend(index: Int): Friend {
    return Friend(
            person = people.get(index)
    )
}

fun generateCustomer(index: Int): Customer {
    return Customer(
            id = people.get(index).id,
            email = people.get(index).email,
            firstname = people.get(index).firstname,
            lastname = people.get(index).lastname,
            address = people.get(index).address,
            user = people.get(index).user,
            backupInfo = "http://${faker.internet.domain()}.de",
            dateOfBirth = Date(
                    Random.nextInt(1950, 2015),
                    Random.nextInt(1, 12),
                    Random.nextInt(1, 30),
            ),
            subscription = subscriptionModel[Random.nextInt(0, subscriptionModel!!.size - 1)],
            achievements = mutableListOf(),
            friend = friends!![Random.nextInt(0, friends!!.size - 1)],
    )
}

fun generateSubscriptionModels() {
    subscriptionModel = mutableListOf(
            SubscriptionModel(
                    name = "STANDARD",
                    price = 0,
                    billingInterval = BillingInterval.YEARLY,
            ),
            SubscriptionModel(
                    name = "PREMIUM",
                    price = 500,
                    billingInterval = BillingInterval.YEARLY
            ),
    )
}

fun generateCompany(index: Int): Company {
    return Company(
            id = people[index + customerCount].id,
            name = faker.company.name(),
            legalForm = "GmbH",
    )
}

fun generateExpenseList(index: Int): MutableList<Expense> {
    return MutableList(customerCount, this.generateExpense(index))

}

fun generateExpense(customerIndex: Int): (Int) -> Expense {
    return expense@{ index ->
        val amount = Random.nextInt(1, 50000)
        return@expense Expense(
                id = UUID.randomUUID(),
                amount = amount,
                merchant = Merchant(faker.company.name()),
                points = amount / 10,
                category = expenseCategories[Random.nextInt(0, expenseCategories!!.size - 1)],
                person = people[customerIndex]
        )
    }
}

fun generateCategory(index: Int): Category {
    index
    return Category(name = faker.company.industry())
}

fun generateCoupon(index: Int): Coupon {
    index
    return Coupon(
            person = people[Random.nextInt(customerCount, peopleCount - 1)],
            code = faker.commerce.promotionCode(),
            minimumOrderValue = Random.nextInt(0, 1000),
            expirationDate = Date(
                    Random.nextInt(2020, 2030),
                    Random.nextInt(1, 12),
                    Random.nextInt(1, 30),
            ),
    )
}

fun generateAchievement(index: Int): Achievement {
    index
    return Achievement(
            id = UUID.randomUUID(),
            name = faker.funnyName.name(),
            description = faker.greekPhilosophers.quotes(),
            isAchieved = false,
    )
}

fun generateOffer(index: Int): Offer {
    return Offer(
            id = UUID.randomUUID(),
            description = faker.commerce.productName(),
            priceModel = pricemodels[index],
            person = people[Random.nextInt(customerCount, peopleCount - 1)],
    )
}

fun generatePriceModel(index: Int): PriceModel {
    index
    return PriceModel(
            price = Random.nextInt(1, 10000),
            isSubscription = Random.nextBoolean(),
            interval = listOf<BillingInterval?>(
                    BillingInterval.YEARLY,
                    BillingInterval.MONTHLY,
                    null
            )[Random.nextInt(0, 2)]
    )
}

fun generateRequest(index: Int): Request {
    index
    return Request(
            person = people[Random.nextInt(0, customerCount)],
            offer = offers[Random.nextInt(0, offerCount)],
    )
}
}