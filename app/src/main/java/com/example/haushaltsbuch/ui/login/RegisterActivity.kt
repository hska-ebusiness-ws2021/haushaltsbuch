package com.example.haushaltsbuch.ui.login

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.haushaltsbuch.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val backButton = findViewById<Button>(R.id.backButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        backButton.setOnClickListener{
            navigateToLogin()
        }

        registerButton.setOnClickListener {
            sendRegister()
        }

    }

    fun navigateToLogin(){
        setContentView(R.layout.activity_login)
    }

    fun sendRegister(){
        setContentView(R.layout.activity_login)
    }
}