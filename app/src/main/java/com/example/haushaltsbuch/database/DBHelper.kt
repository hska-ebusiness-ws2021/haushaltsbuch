package com.example.haushaltsbuch.database

import com.example.haushaltsbuch.data.model.finances.*
import com.example.haushaltsbuch.data.model.persons.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.collections.ArrayList

class DBHelper {

    /*
    * Database operations helper class
    *
    * function for get all users in the database
    * */

    fun getAllUser(): ArrayList<User> {
        val users: ArrayList<User> = arrayListOf()
        transaction {
            Users.selectAll().map {
                users.add(
                    User(
                        username = it[Users.username],
                        password = it[Users.password]
                    )
                )
            }
        }
        return users
    }

    /*
    * function to get one user from database
    * */

    fun getUser(username: String) = transaction {
        Users.select { Users.username.eq(username) }
    }

    /*
    * function to add user to database
    * */

    fun addUser(user: User) = transaction {
        Users.insert {
            it[this.username] = user.username
            it[this.password] = user.password
        }
    }

    /*
    * function to change username
    * */

    fun changeUsername(username: String) = transaction {
        Users.update({ Users.username.eq(username) }) {
            it[this.username] = username
        }
    }

    /*
    * function to delete user from database
    * */

    fun deleteUser(username: String) = transaction {
        Users.deleteWhere { Users.username.eq(username) }
    }

    /*
    * function to add person during registration
    * */

    fun addPerson(person: Person) = transaction {
        Persons.insert {
            it[this.id] = UUID.randomUUID()
            it[this.firstname] = person.firstname
            it[this.lastname] = person.lastname
            it[this.email] = person.email
            it[this.username] = person.username
            it[this.password] = person.email
        }
    }

    /*
    * function to delete person
    * */

    fun deletePerson(id: UUID) = transaction {
        Persons.deleteWhere { Persons.id.eq(id) }
    }

    /*
    * function to reset password
    * */

    fun resetPassword(pwd: String) = transaction {
        Users.update({ (Users.password.eq(pwd)) }) {
            it[this.password] = pwd
        }
    }

    /*
    * function to add new category
    * */

    fun addCategory(category: Category) = transaction {
        Categories.insert { it[this.name] = category.name }
    }

    /*
    * function to update a category
    * */

    fun updateCategory(name: String) = transaction {
        Categories.update({ Categories.name.eq(name) }) {
            it[this.name] = name
        }
    }

    /*
    * function to delete one category
    * */

    fun deleteCategory(name: String) = transaction {
        Categories.deleteWhere { Categories.name.eq(name) }
    }

    /*
    * function to add new expense
    * */

    fun addExpense(expense: Expense) = transaction {
        Expenses.insert {
            it[this.amount] = expense.amount
            it[this.date] = expense.date
        }
    }

    /*
    * function to update expense
    * */

    fun updateExpense(id: UUID, expense: Expense) = transaction {
        Expenses.update({ Expenses.id.eq(id) }) {
            it[this.amount] = expense.amount
            it[this.date] = expense.date
        }
    }

    /*
    * function to delete expense
    * */

    fun deleteExpense(id: UUID) = transaction {
        Expenses.deleteWhere { Expenses.id.eq(id) }
    }
}