package com.example.haushaltsbuch.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        Log.i("REGISTER", "RegisterActivity1")
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
            userRegister(it)
        }
    }

    fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun userRegister(view: View) {
        Log.i("REGISTER", "RegisterActivity2")
        //binding.apply {
        val id = UUID.randomUUID()
        val firstname = binding.editTextTextPersonName.toString()
        val lastname = binding.editTextTextPersonName2.toString()
        val email = binding.editTextTextEmailAddress.toString()
        val username = binding.editTextTextPersonName3.toString()
        val password = binding.editTextTextPassword2.toString()
        val passwordConfirm = binding.editTextTextPassword.toString()
        val dbHelper = DBHelper()

        //if (firstname != "" && lastname != "" && email != "" && username != "" && password != "" && passwordConfirm != "" && password == passwordConfirm) {
        dbHelper.addUser(User(username, password))
        dbHelper.addPerson(
            Person(
                id,
                firstname,
                lastname,
                email,
                (User(username, password))
            )
        )
        dbHelper.getAllUser()
        //after onClick
        binding.editTextTextPersonName.setText("")
        binding.editTextTextPersonName2.setText("")
        binding.editTextTextEmailAddress.setText("")
        binding.editTextTextPersonName3.setText("")
        binding.editTextTextPassword2.setText("")
        binding.editTextTextPassword.setText("")
        //}

        navigateToLogin()
        //}
    }
}