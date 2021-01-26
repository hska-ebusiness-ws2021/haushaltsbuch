package com.example.haushaltsbuch.database

import android.util.Log
import com.example.haushaltsbuch.data.model.finances.*
import com.example.haushaltsbuch.data.model.persons.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.collections.ArrayList

/*
 *Class for database queries.
 *
 * */

class DBHelper {

    /**
     * query to get all userdata from database.
     *
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
    * query to get username from database.
    * */

    fun getUser(username: String) {
        Log.i("QUERY", "getUser()")
        transaction {
            Users.select { Users.username.eq(username) }
        }
    }

    /*
    * query to get user password from database.
    * */

    fun getUserPassword(pwd: String) {
        Log.i("QUERY", "getUserPassword()")
        transaction {
            Users.select { Users.password.eq(pwd) }
        }
    }

    /*
    * query to reset user password.
   * */

    fun resetPassword(pwd: String) = transaction {
        Users.update({ (Users.password.eq(pwd)) }) {
            it[this.password] = pwd
        }
    }

    /*
    * query to add user to database
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
    * query to change username
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
    * query to delete user from database
    * */

    fun deleteUser(username: String) {
        Log.i("QUERY", "deleteUser()")
        transaction {
            Users.deleteWhere { Users.username.eq(username) }
        }
    }

    /*
    * query to add person during registration
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
    * query to delete person
    * */

    fun deletePerson(id: UUID) = transaction {
        Persons.deleteWhere { Persons.id.eq(id) }
    }


    /*
    * query to add new category.
    * */

    fun addCategory(category: Category) {
        Log.i("QUERY", "addCategory()")
        transaction {
            Categories.insert { it[this.name] = category.name }
        }
    }

    /*
    * query to get category.
    * */

    fun getCategory(name: String) {
        Log.i("QUERY", "getCategory()")
        transaction {
            Categories.select { Categories.name.eq(name) }
        }
    }

    /*
    * query to update a category.
    * */

    fun updateCategory(name: String) = transaction {
        Categories.update({ Categories.name.eq(name) }) {
            it[this.name] = name
        }
    }

    /*
    * query to delete one category.
    * */

    fun deleteCategory(name: String) = transaction {
        Categories.deleteWhere { Categories.name.eq(name) }
    }

    /*
    * query to add new expense.
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
    * query to update expense.
    * */

    fun updateExpense(id: UUID, expense: Expense) = transaction {
        Expenses.update({ Expenses.id.eq(id) }) {
            it[this.amount] = expense.amount
            it[this.date] = expense.date
        }
    }

    /*
    * query to delete expense.
    * */

    fun deleteExpense(id: UUID) = transaction {
        Expenses.deleteWhere { Expenses.id.eq(id) }
    }
}