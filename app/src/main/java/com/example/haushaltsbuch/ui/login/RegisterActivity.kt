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

        val backButton = findViewById<Button>(R.id.registerBackButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        backButton.setOnClickListener {
            navigateToLogin()
        }

        registerButton.setOnClickListener {
            sendRegister()
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

    fun register(view: View) {
        val id = UUID.randomUUID()
        val firstname = binding.editTextTextPersonName.toString()
        val lastname = binding.editTextTextPersonName2.toString()
        val email = binding.editTextTextEmailAddress.toString()
        val username = binding.editTextTextPersonName3.toString()
        val password = binding.editTextTextPassword2.toString()
        val passwordConfirm = binding.editTextNumberPassword.toString()
        val databaseHandler: DBHelper = DBHelper()

        if (firstname != "" && lastname != "" && email != "" && username != "" && password != "" && passwordConfirm != "" && password.equals(
                passwordConfirm
            )
        ) {
            val status = databaseHandler.addPerson(
                Person(id, firstname, lastname, email, username, password)
            )
            //after onClick
            binding.editTextTextPersonName.clearComposingText()
            binding.editTextTextPersonName2.clearComposingText()
            binding.editTextTextEmailAddress.clearComposingText()
            binding.editTextTextPersonName3.clearComposingText()
            binding.editTextTextPassword2.clearComposingText()
            binding.editTextNumberPassword.clearComposingText()
        }
    }
}