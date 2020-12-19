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

class FakeData() {

    val locations = MutableList<Location>(LOCATION_COUNT, ::generateLocation)
    val postCode = MutableList<PostCode>(POSTCODE_COUNT, ::generatePostCode)
    val street = MutableList<Street>(STREET_COUNT, ::generateStreet)
    val addresses = MutableList<Address>(ADDRESS_COUNT, ::generateAddress)
    val users = MutableList(PEOPLE_COUNT, ::generateUser)
    val roles = generateRoles()
    val people = MutableList<Person>(PEOPLE_COUNT, ::generatePerson)
    val friends = MutableList<Friend>(PEOPLE_COUNT, ::generateFriend)
    val subscriptionModels = generateSubscriptionModels()
    val customers = MutableList<Customer>(CUSTOMER_COUNT, ::generateCustomer)
    val companies = MutableList<Company>(COMPANY_COUNT, ::generateCompany)
    val expenseCategories = MutableList<Category>(CATEGORY_COUNT, ::generateCategory)
    val expenses = MutableList<MutableList<Expense>>(CUSTOMER_COUNT, ::generateExpenseList)
    val coupon = MutableList<Coupon>(COUPON_COUNT, ::generateCoupon)
    val achievements = MutableList<Achievement>(ACHIEVEMENT_COUNT, ::generateAchievement)
    val pricemodels = MutableList<PriceModel>(OFFER_COUNT, ::generatePriceModel)
    val offers = MutableList<Offer>(OFFER_COUNT, ::generateOffer)
    val requests = MutableList<Request>(REQUEST_COUNT, ::generateRequest)


    private fun generatePerson(index: Int): Person {
        val name = faker.name.firstName()
        val surname = faker.name.lastName()
        val domain = faker.company.name()
        return Person(
            id = UUID.randomUUID(),
            firstname = name,
            lastname = surname,
            email = "$name.$surname@$domain.de",
            address = addresses[Random.nextInt(0, ADDRESS_COUNT -1)],
            user = users[index],

            )
    }

    private fun generateAddress(index: Int): Address {
        return Address(
            postCode = postCode[Random.nextInt(0, POSTCODE_COUNT - 1)],
            street = street[Random.nextInt(0, STREET_COUNT - 1)],
            person = null,
        )
    }

    private fun generatePostCode(index: Int): PostCode {
        return PostCode(
            postCode = faker.address.postcode(),
            location = locations[Random.nextInt(0, LOCATION_COUNT -1)],
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
            role = listOf(roles[Random.nextInt(0, 1)]),
        )
    }


    private fun generateRoles(): MutableList<Role> {
        return mutableListOf<Role>(Role.STANDARD, Role.PREMIUM)
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
            firstname = people[index].firstname,
            lastname = people[index].lastname,
            address = people[index].address,
            user = people[index].user,
            backupInfo = "http://${faker.internet.domain()}.de",
            dateOfBirth = Date(
                Random.nextInt(1950, 2015),
                Random.nextInt(1, 12),
                Random.nextInt(1, 30),
            ),
            subscription = subscriptionModels[Random.nextInt(0, subscriptionModels.size - 1)],
            achievements = mutableListOf(),
            friend = friends[Random.nextInt(0, friends.size - 1)],
        )
    }

    private fun generateSubscriptionModels(): MutableList<SubscriptionModel> {
        return  mutableListOf(
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
            id = people[index + CUSTOMER_COUNT].id,
            name = faker.company.name(),
            legalForm = "GmbH",
        )
    }

    private fun generateExpenseList(index: Int): MutableList<Expense> {
        return MutableList(CUSTOMER_COUNT, this.generateExpense(index))

    }

    private fun generateExpense(customerIndex: Int): (Int) -> Expense {
        return expense@{ index ->
            val amount = Random.nextInt(1, 50000)
            return@expense Expense(
                id = UUID.randomUUID(),
                amount = amount,
                merchant = Merchant(faker.company.name()),
                points = amount / 10,
                category = expenseCategories[Random.nextInt(0, expenseCategories.size - 1)],
                person = people[customerIndex]
            )
        }
    }

    private fun generateCategory(index: Int): Category {
        return Category(name = faker.company.industry())
    }

    private fun generateCoupon(index: Int): Coupon {
        return Coupon(
            person = people[Random.nextInt(CUSTOMER_COUNT, PEOPLE_COUNT - 1)],
            code = faker.commerce.promotionCode(),
            minimumOrderValue = Random.nextInt(0, 1000),
            expirationDate = Date(
                Random.nextInt(2020, 2030),
                Random.nextInt(1, 12),
                Random.nextInt(1, 30),
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
            person = people[Random.nextInt(CUSTOMER_COUNT, PEOPLE_COUNT - 1)],
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
            person = people[Random.nextInt(0, CUSTOMER_COUNT)],
            offer = offers[Random.nextInt(0, OFFER_COUNT)],
        )
    }

    companion object {
        private const val ADDRESS_COUNT = 50
        private const val PEOPLE_COUNT = 50
        private const val POSTCODE_COUNT = 5
        private const val STREET_COUNT = 10
        private const val LOCATION_COUNT = 5
        private const val COMPANY_COUNT = 10
        private const val CUSTOMER_COUNT = PEOPLE_COUNT - COMPANY_COUNT
        private const val CATEGORY_COUNT = 10
        private const val COUPON_COUNT = 20
        private const val ACHIEVEMENT_COUNT = 20
        private const val OFFER_COUNT = 20
        private const val REQUEST_COUNT = 40
    }
}