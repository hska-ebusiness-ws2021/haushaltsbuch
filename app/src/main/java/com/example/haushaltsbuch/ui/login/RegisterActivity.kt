package com.example.haushaltsbuch.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.haushaltsbuch.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_register)
        super.onCreate(savedInstanceState)

        val backButton = findViewById<Button>(R.id.registerBackButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        backButton.setOnClickListener{
            navigateToLogin()
        }

        registerButton.setOnClickListener {
            sendRegister()
        }

    }

    fun navigateToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun sendRegister(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}