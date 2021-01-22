package com.example.haushaltsbuch.database

import com.example.haushaltsbuch.data.model.persons.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.collections.ArrayList

class DBHelper {

    /*
    * Database operations helper class
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

    fun getUser(username: String) = transaction {
        // Users.select(Users.username eq username)
    }

    fun addUser(user: User) = transaction {
        Users.insert {
            it[this.username] = user.username
            it[this.password] = user.password
        }
    }

    fun changeUsername(username: String) = transaction {
        Users.update({ Users.username eq username }) {
            it[this.username] = username
        }
    }

    fun resetPassword(password: String) = transaction {
        Users.update({ Users.password eq password }) {
            it[this.password] = password
        }
    }


    fun deleteUser(username: String) = transaction {
        Users.deleteWhere { Users.username eq username }
    }

    fun addPerson(person: Person) = transaction {
        Persons.insert {
            it[this.firstname] = person.firstname
            it[this.lastname] = person.lastname
            it[this.email] = person.email
            //TODO User
        }
    }

    fun deletePerson(id: UUID) = transaction {
        //Persons.deleteWhere { Persons.id eq id }
    }

}