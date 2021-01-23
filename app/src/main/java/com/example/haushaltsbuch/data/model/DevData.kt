package com.example.haushaltsbuch.data.model

import com.example.haushaltsbuch.data.model.finances.Category
import com.example.haushaltsbuch.data.model.finances.Expense
import com.example.haushaltsbuch.data.model.gamification.Achievement
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

val fakerConfig = FakerConfig.builder().create { }

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
    val users = List(peopleCount, ::generateUser)
    val people = List<Person>(peopleCount, ::generatePerson)
    val subscriptions = generateSubscriptionModels()
    val customers = List<Customer>(customerCount, ::generateCustomer)
    val expenseCategories = List<Category>(categoryCount, ::generateCategory)
    val expenses = List<List<Expense>>(customerCount, ::generateExpenseList)
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
            user = users[index],

            )
    }

    private fun generateUser(index: Int): User {
        return User(
                username = faker.funnyName.name(),
                password = "password",
        )
    }

    private fun generateCustomer(index: Int): Customer {
        return Customer(
                id = people[index].id,
                email = people[index].email,
                firstname =  people[index].firstname,
                lastname = people[index].lastname,
                user = people[index].user,
                backupInfo = "http://${faker.internet.domain()}.de",
                dateOfBirth = Date(
                        Random.nextInt(1950,2015),
                        Random.nextInt(1,12),
                        Random.nextInt(1,30),
                ),
                subscription = subscriptions[Random.nextInt(0,subscriptions.size-1)],
                achievements = mutableListOf(),
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

    private fun generateExpenseList(index: Int): List<Expense>{
        return List(customerCount, this.generateExpense(index))

    }

    /**
     * depends on expenseCategories and people
     */
    private fun generateExpense(customerIndex: Int): (Int) -> Expense {
        return expense@{ index ->
            val amount = Random.nextInt(1, 50000)
            return@expense Expense(
                    id = UUID.randomUUID(),
                    amount = BigDecimal(amount),
                    points = amount / 10,
                    category = expenseCategories[Random.nextInt(0,expenseCategories.size-1)],
                    person = people[customerIndex]
            )
        }
    }

    private fun generateCategory(index: Int): Category {
        return Category(name = faker.company.industry())
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
                person = people[Random.nextInt(customerCount-1, peopleCount-1)],
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