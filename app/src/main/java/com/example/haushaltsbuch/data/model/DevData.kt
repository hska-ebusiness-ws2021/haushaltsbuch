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
    // Data
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

    var expenses: MutableList<MutableList<Expense>>? = null
    var expenseCategories: MutableList<Category>? = null

    var coupon: MutableList<Coupon>? = null
    var achievements: MutableList<Achievement>? = null

    var requests: MutableList<Request>? = null
    var offers: MutableList<Offer>? = null
    var pricemodels: MutableList<PriceModel>? = null

    // amount of Data
    val addressCount = 50
    val peopleCount = 50
    val postcodeCount = 5
    val streetcount = 10
    val locationCount = 5
    val companyCount = 10
    val customerCount = peopleCount - companyCount
    val categoryCount = 10
    val couponCount = 20
    val achievementCount = 20
    val offerCount = 20
    val requestCount = 40

    // generate

    // main generate
    fun generateData(){
        locations = MutableList<Location>(locationCount, ::generateLocation)
        postCode = MutableList<PostCode>(postcodeCount, ::generatePostCode)
        street = MutableList<Street>(streetcount, ::generateStreet)
        addresses = MutableList<Address>(50, ::generateAddress)
        generateRoles()
        users = MutableList(peopleCount, ::generateUser)
        people = MutableList<Person>(peopleCount, ::generatePerson)
        friends = MutableList<Friend>(peopleCount, ::generateFriend)
        generateSubscriptionModels()
        customers = MutableList<Customer>(customerCount, ::generateCustomer)
        companies = MutableList<Company>(companyCount, ::generateCompany)
        expenseCategories = MutableList<Category>(categoryCount, ::generateCategory)
        expenses = MutableList<MutableList<Expense>>(customerCount, ::generateExpenseList)
        coupon = MutableList<Coupon>(couponCount, ::generateCoupon)
        achievements = MutableList<Achievement>(achievementCount, ::generateAchievement)
        pricemodels = MutableList<PriceModel>(offerCount, ::generatePriceModel)
        offers = MutableList<Offer>(offerCount, ::generateOffer)
        requests = MutableList<Request>(requestCount, ::generateRequest)
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
                role = roles!![Random.nextInt(0,1)],
        )
    }

    fun generateRoles(){
        roles = mutableListOf(mutableListOf( Role.STANDARD), mutableListOf( Role.PREMIUM))
        return
    }

    fun generateFriend(index: Int): Friend{
        return Friend(
                person = people!![index]
        )
    }

    fun generateCustomer(index: Int): Customer {
        return Customer(
                id = people!![index].id,
                email = people!![index].email,
                firstname =  people!![index].firstname,
                lastname = people!![index].lastname,
                address = people!![index].address,
                user = people!![index].user,
                backupInfo = "http://${faker.internet.domain()}.de",
                dateOfBirth = Date(
                        Random.nextInt(1950,2015),
                        Random.nextInt(1,12),
                        Random.nextInt(1,30),
                ),
                subscription = subscriptionModel!![Random.nextInt(0,subscriptionModel!!.size-1)],
                achievements = mutableListOf(),
                friend = friends!![Random.nextInt(0,friends!!.size-1)],
        )
    }

    fun generateSubscriptionModels(){
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

    fun generateCompany(index: Int): Company{
        return Company(
                id = people!![index+customerCount].id,
                name = faker.company.name(),
                legalForm = "GmbH",
        )
    }

    fun generateExpenseList(index: Int): MutableList<Expense>{
        return MutableList(customerCount, this.generateExpense(index))

    }

    fun generateExpense(customerIndex: Int): (Int)->Expense {
        return expense@{ index ->
            val amount = Random.nextInt(1,50000)
            return@expense Expense(
                    id = UUID.randomUUID(),
                    amount = amount,
                    merchant = Merchant(faker.company.name()),
                    points = amount / 10,
                    category = expenseCategories!![Random.nextInt(0,expenseCategories!!.size-1)],
                    person = people!![customerIndex]
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
                person = people!![Random.nextInt(customerCount, peopleCount-1)],
                code = faker.commerce.promotionCode(),
                minimumOrderValue = Random.nextInt(0,1000),
                expirationDate = Date(
                        Random.nextInt(2020,2030),
                        Random.nextInt(1,12),
                        Random.nextInt(1,30),
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
                priceModel = pricemodels!![index],
                person = people!![Random.nextInt(customerCount, peopleCount-1)],
        )
    }

    fun generatePriceModel(index: Int): PriceModel {
        index
        return PriceModel(
                price = Random.nextInt(1,10000),
                isSubscription = Random.nextBoolean(),
                interval = listOf<BillingInterval?>(
                        BillingInterval.YEARLY,
                        BillingInterval.MONTHLY,
                        null
                )[Random.nextInt(0,2)]
        )
    }

    fun generateRequest(index: Int): Request {
        index
        return Request(
                person = people!![Random.nextInt(0,customerCount)],
                offer = offers!![Random.nextInt(0, offerCount)],
        )
    }
}