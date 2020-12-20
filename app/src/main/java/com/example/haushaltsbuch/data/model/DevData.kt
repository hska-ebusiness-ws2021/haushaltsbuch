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
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

val fakerConfig = FakerConfig.builder().create { locale = "de" }

val faker = Faker(fakerConfig)

class FakeData (
    private val addressCount: Int = 50,
    private val peopleCount: Int = 50,
    private val postcodeCount: Int = 5,
    private val streetcount: Int = 10,
    private val locationCount: Int = 5,
    private val companyCount: Int = 10,
    private val categoryCount: Int = 10,
    private val couponCount: Int = 20,
    private val achievementCount: Int = 20,
    private val offerCount: Int = 20,
    private val requestCount: Int = 40,
    ){

    val customerCount = peopleCount - companyCount

// Data
    val locations = List<Location>(locationCount, ::generateLocation)
    val postCode = List<PostCode>(postcodeCount, ::generatePostCode)
    val street = List<Street>(streetcount, ::generateStreet)
    val addresses = List<Address>(addressCount, ::generateAddress)
    val roles = generateRoles()
    val users = List(peopleCount, ::generateUser)
    val people = List<Person>(peopleCount, ::generatePerson)
    val friends = List<Friend>(peopleCount, ::generateFriend)
    val subscriptions = generateSubscriptionModels()
    val customers = List<Customer>(customerCount, ::generateCustomer)
    val companies = List<Company>(companyCount, ::generateCompany)
    val expenseCategories = List<Category>(categoryCount, ::generateCategory)
    val expenses = List<List<Expense>>(customerCount, ::generateExpenseList)
    val coupon = List<Coupon>(couponCount, ::generateCoupon)
    val achievements = List<Achievement>(achievementCount, ::generateAchievement)
    val pricemodels = List<PriceModel>(offerCount, ::generatePriceModel)
    val offers = List<Offer>(offerCount, ::generateOffer)
    val requests = List<Request>(requestCount, ::generateRequest)


    private fun generatePerson(index: Int): Person  {
        val name = faker.name.firstName()
        val surname = faker.name.lastName()
        val domain = faker.company.name()
        return Person(
            id = UUID.randomUUID(),
            firstname = name,
            lastname = surname,
            email = "$name.$surname@$domain.de",
            address = addresses[Random.nextInt(0, addressCount -1)],
            user = users[index],

            )
    }

    private fun generateAddress(index: Int): Address {
        return Address(
            postCode = postCode[Random.nextInt(0, postcodeCount - 1)],
            street = street[Random.nextInt(0, streetcount - 1)],
            person = null,
        )
    }

    private fun generatePostCode(index: Int): PostCode {
        return PostCode(
            postCode = faker.address.postcode(),
            location = locations[Random.nextInt(0, locationCount -1)],
        )
    }

    private fun generateLocation(index: Int): Location {
        return Location(name = faker.address.city())
    }

    private fun generateStreet(index: Int): Street {
        return Street(
            name = faker.address.streetName(),
            houseNumber = index.toString(),
            additionalInfo = null,
        )
    }

    private fun generateUser(index: Int): User {
        return User(
                username = faker.funnyName.name(),
                password = "password",
                role = roles[Random.nextInt(0,1)],
        )
    }

    private fun generateRoles(): List<List<Role>>{
        return listOf(listOf( Role.STANDARD), listOf( Role.PREMIUM))
    }

    private fun generateFriend(index: Int): Friend {
        return Friend(
            person = people[index]
        )
    }

    private fun generateCustomer(index: Int): Customer {
        return Customer(
                id = people[index].id,
                email = people[index].email,
                firstname =  people[index].firstname,
                lastname = people[index].lastname,
                address = people[index].address,
                user = people[index].user,
                backupInfo = "http://${faker.internet.domain()}.de",
                dateOfBirth = Date(
                        Random.nextInt(1950,2015),
                        Random.nextInt(1,12),
                        Random.nextInt(1,30),
                ),
                subscription = subscriptions[Random.nextInt(0,subscriptions.size-1)],
                achievements = mutableListOf(),
                friend = friends[Random.nextInt(0,friends.size-1)],
        )
    }

    private fun generateSubscriptionModels(): List<SubscriptionModel>{
        return listOf(
                SubscriptionModel(
                name = "STANDARD",
                price = BigDecimal(0),
                billingInterval = BillingInterval.YEARLY,
            ),
            SubscriptionModel(
                name = "PREMIUM",
                price = BigDecimal(500),
                billingInterval = BillingInterval.YEARLY
            ),
        )
    }

    private fun generateCompany(index: Int): Company {
        return Company(
                id = people[index+customerCount].id,
                name = faker.company.name(),
                legalForm = "GmbH",
        )
    }

    private fun generateExpenseList(index: Int): List<Expense>{
        return List(customerCount, this.generateExpense(index))

    }

    private fun generateExpense(customerIndex: Int): (Int) -> Expense {
        return expense@{ index ->
            val amount = Random.nextInt(1, 50000)
            return@expense Expense(
                    id = UUID.randomUUID(),
                    amount = BigDecimal(amount),
                    merchant = Merchant(faker.company.name()),
                    points = amount / 10,
                    category = expenseCategories[Random.nextInt(0,expenseCategories.size-1)],
                    person = people[customerIndex]
            )
        }
    }

    private fun generateCategory(index: Int): Category {
        return Category(name = faker.company.industry())
    }

    private fun generateCoupon(index: Int): Coupon {
        return Coupon(
                person = people[Random.nextInt(customerCount, peopleCount-1)],
                code = faker.commerce.promotionCode(),
                minimumOrderValue = BigDecimal(Random.nextInt(0,1000)),
                expirationDate = Date(
                        Random.nextInt(2020,2030),
                        Random.nextInt(1,12),
                        Random.nextInt(1,30),
                ),
        )
    }

    private fun generateAchievement(index: Int): Achievement {
        return Achievement(
            id = UUID.randomUUID(),
            name = faker.funnyName.name(),
            description = faker.greekPhilosophers.quotes(),
            isAchieved = false,
        )
    }

    private fun generateOffer(index: Int): Offer {
        return Offer(
                id = UUID.randomUUID(),
                description = faker.commerce.productName(),
                priceModel = pricemodels[index],
                person = people[Random.nextInt(customerCount, peopleCount-1)],
        )
    }

    private fun generatePriceModel(index: Int): PriceModel {
        return PriceModel(
            price = BigDecimal(Random.nextInt(1, 10000)),
            isSubscription = Random.nextBoolean(),
            interval = listOf<BillingInterval?>(
                BillingInterval.YEARLY,
                BillingInterval.MONTHLY,
                null
            )[Random.nextInt(0, 2)]
        )
    }

    private fun generateRequest(index: Int): Request {
        return Request(
                person = people[Random.nextInt(0,customerCount)],
                offer = offers[Random.nextInt(0, offerCount)],
        )
    }
}