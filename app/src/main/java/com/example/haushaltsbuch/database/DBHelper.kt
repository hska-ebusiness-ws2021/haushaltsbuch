package com.example.haushaltsbuch.database

import android.util.Log
import com.example.haushaltsbuch.data.model.finances.*
import com.example.haushaltsbuch.data.model.persons.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.collections.ArrayList

/*
 * Database operations helper class
 * */

class DBHelper {
    /**
     * +++Queries+++
     *
     * get all userdata from database
     */

    fun getAllUser(): ArrayList<User> {
        Log.i("QUERY", "getAllUser()")
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
    * function to get username from database
    * */

    fun getUser(username: String) {
        Log.i("QUERY", "getUser()")
        transaction {
            Users.select { Users.username.eq(username) }
        }
    }

    /*
    * function to get user password from database
    * */

    fun getUserPassword(pwd: String) {
        Log.i("QUERY", "getUserPassword()")
        transaction {
            Users.select { Users.password.eq(pwd) }
        }
    }

    /*
   * function to reset user password
   * */

    fun resetPassword(pwd: String) = transaction {
        Users.update({ (Users.password.eq(pwd)) }) {
            it[this.password] = pwd
        }
    }

    /*
    * function to add user to database
    * */

    fun addUser(user: User) {
        Log.i("QUERY", "addUser()")
        transaction {
            Users.insert {
                it[this.username] = user.username
                it[this.password] = user.password
            }
        }
    }

    /*
    * function to change username
    * */

    fun changeUsername(username: String) {
        Log.i("QUERY", "changeUsername()")
        transaction {
            Users.update({ Users.username.eq(username) }) {
                it[this.username] = username
            }
        }
    }

    /*
    * function to delete user from database
    * */

    fun deleteUser(username: String) {
        Log.i("QUERY", "deleteUser()")
        transaction {
            Users.deleteWhere { Users.username.eq(username) }
        }
    }

    /*
    * function to add person during registration
    * */

    fun addPerson(person: Person) {
        Log.i("QUERY", "addPerson()")
        transaction {
            Persons.insert {
                it[this.id] = person.id
                it[this.firstname] = person.firstname
                it[this.lastname] = person.lastname
                it[this.email] = person.email
                it[this.username] = person.user.username
                it[this.password] = person.user.password
            }
            println("Persons: ${Persons.selectAll()}")
        }
    }

    /*
    * function to delete person
    * */

    fun deletePerson(id: UUID) = transaction {
        Persons.deleteWhere { Persons.id.eq(id) }
    }


    /*
    * function to add new category
    * */

    fun addCategory(category: Category) {
        Log.i("QUERY", "addCategory()")
        transaction {
            Categories.insert { it[this.name] = category.name }
        }
    }

    fun getCategory(name: String) {
        Log.i("QUERY", "getCategory()")
        transaction {
            Categories.select { Categories.name.eq(name) }
        }
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

    fun addExpense(expense: Expense) {
        Log.i("QUERY", "addExpense()")
        transaction {
            Expenses.insert {
                it[this.id] = expense.id
                it[this.amount] = expense.amount
                it[this.title] = expense.title
                it[this.category] = expense.category.name
                it[this.date] = expense.date
            }
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