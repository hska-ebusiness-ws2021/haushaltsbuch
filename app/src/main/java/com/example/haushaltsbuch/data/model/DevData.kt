package com.example.haushaltsbuch.data.model

import com.example.haushaltsbuch.data.model.finances.*
import com.example.haushaltsbuch.data.model.gamification.*
import com.example.haushaltsbuch.data.model.mediation.*
import com.example.haushaltsbuch.data.model.persons.*
import org.joda.time.DateTime
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

class DevData(
    private var categoryCount: Int = 10,
    private var achievementCount: Int = 20,
    private var offerCount: Int = 20,
    private var requestCount: Int = 40,
) {

    var peopleCount = 4
    var customerCount = 3

    // Data
    var users = List(peopleCount, ::generateUser)
    var people = List<Person>(peopleCount, ::generatePerson)
    var subscriptions = generateSubscriptionModels()
    var customers = List<Customer>(customerCount, ::generateCustomer)
    var expenseCategories = List<Category>(categoryCount, ::generateCategory)
    var expenses = List<List<Expense>>(customerCount, ::generateExpenseList)
    var achievements = List<Achievement>(achievementCount, ::generateAchievement)
    var pricemodels = List<PriceModel>(offerCount, ::generatePriceModel)
    var offers = List<Offer>(offerCount, ::generateOffer)
    var requests = List<Request>(requestCount, ::generateRequest)

    private fun generatePerson(index: Int): Person {

        var name = "Bernt"
        var surname = "Sieberts"
        var domain = "acme"

        return Person(
            id = UUID.randomUUID(),
            firstname = name,
            lastname = surname,
            email = "$name.$surname@$domain.de",
            user = users[index]
        )

    }

    private fun generateUser(index: Int): User {
        return User(
            username = "Bernle",
            password = "password"
        )
    }

    private fun generateCustomer(index: Int): Customer {
        return Customer(
            id = people[index].id,
            email = people[index].email,
            firstname = people[index].firstname,
            lastname = people[index].lastname,
            user = people[index].user,
            backupInfo = "http://acme.de",
            dateOfBirth = DateTime(
                Random.nextInt(1950, 2015),
                Random.nextInt(1, 12),
                Random.nextInt(1, 30),
                Random.nextInt(1, 24),
                Random.nextInt(1, 60)
            ),
            subscription = subscriptions[Random.nextInt(0, subscriptions.size - 1)],
            achievements = mutableListOf()
        )
    }

    private fun generateSubscriptionModels(): List<SubscriptionModel> {
        return listOf(
            SubscriptionModel(
                id = UUID.randomUUID(),
                name = "STANDARD",
                price = BigDecimal(0),
                billingInterval = BillingInterval.YEARLY,
            ),
            SubscriptionModel(
                id = UUID.randomUUID(),
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
            var amount = Random.nextInt(1, 50000)
            return@expense Expense(
                id = UUID.randomUUID(),
                amount = BigDecimal(amount),
                points = amount / 10,
                category = expenseCategories[Random.nextInt(0, expenseCategories.size - 1)],
                person = people[customerIndex],
                date = DateTime(
                    Random.nextInt(1950, 2015),
                    Random.nextInt(1, 12),
                    Random.nextInt(1, 30),
                    Random.nextInt(1, 24),
                    Random.nextInt(1, 60)
                )
            )
        }
    }

    private fun generateCategory(index: Int): Category {
        return Category(name = "Haushalt")
    }

    private fun generateAchievement(index: Int): Achievement {
        return Achievement(
            id = UUID.randomUUID(),
            name = "Erstelle eine Ausgabe",
            description = "Erstelle Ausgaben",
            isAchieved = false,
        )
    }

    private fun generateOffer(index: Int): Offer {
        return Offer(
            id = UUID.randomUUID(),
            description = "Tisch",
            priceModel = pricemodels[index],
            person = people[Random.nextInt(customerCount - 1, peopleCount - 1)]
        )
    }

    private fun generatePriceModel(index: Int): PriceModel {
        return PriceModel(
            id = UUID.randomUUID(),
            price = BigDecimal(Random.nextInt(1, 10000)),
            isSubscription = Random.nextBoolean(),
            intervar = listOf<BillingInterval>(
                BillingInterval.YEARLY,
                BillingInterval.MONTHLY,
            )[Random.nextInt(0, 2)]
        )
    }

    private fun generateRequest(index: Int): Request {
        return Request(
            person = people[Random.nextInt(0, customerCount)],
            offer = offers[Random.nextInt(0, offerCount)],
        )
    }
}