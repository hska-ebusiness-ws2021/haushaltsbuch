package com.example.haushaltsbuch.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.haushaltsbuch.R
import com.example.haushaltsbuch.data.model.persons.*
import com.example.haushaltsbuch.database.DBHelper
import com.example.haushaltsbuch.databinding.ActivityRegisterBinding
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val backButton = binding.registerBackButton
        val registerButton = binding.registerButton

        backButton.setOnClickListener {
            navigateToLogin()
        }

        registerButton.setOnClickListener {
            sendRegister()
        }

        binding.registerButton.setOnClickListener {
            userRegister(it)
        }
    }

    fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun sendRegister() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun userRegister(view: View) {
        binding.apply {
            val id = UUID.randomUUID()
            val firstname = binding.editTextTextPersonName.toString()
            val lastname = binding.editTextTextPersonName2.toString()
            val email = binding.editTextTextEmailAddress.toString()
            val username = binding.editTextTextPersonName3.toString()
            val password = binding.editTextTextPassword2.toString()
            val passwordConfirm = binding.editTextNumberPassword.toString()
            val dbHelper: DBHelper = DBHelper()

            if (firstname != "" && lastname != "" && email != "" && username != "" && password != "" && passwordConfirm != "" && password.equals(
                    passwordConfirm
                )
            ) {

                val addPerson = dbHelper.addPerson(
                    Person(
                        id,
                        firstname,
                        lastname,
                        email,
                        dbHelper.addUser(User(username, password))
                    )
                )

                //after onClick
                binding.editTextTextPersonName.setText("")
                binding.editTextTextPersonName2.setText("")
                binding.editTextTextEmailAddress.setText("")
                binding.editTextTextPersonName3.setText("")
                binding.editTextTextPassword2.setText("")
                binding.editTextNumberPassword.setText("")
            }
        }
    }
}