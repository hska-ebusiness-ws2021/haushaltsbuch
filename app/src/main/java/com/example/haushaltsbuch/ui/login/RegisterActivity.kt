package com.example.haushaltsbuch.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.haushaltsbuch.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        print("onCreate")

        super.onCreate(savedInstanceState)

        val backButton = findViewById<Button>(R.id.registerBackButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        backButton.setOnClickListener{
        print("setListener")
            navigateToLogin()
        }

        registerButton.setOnClickListener {
            sendRegister()
        }

    }

    fun navigateToLogin(){
        print("navigateToLogin")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun sendRegister(){
        print("sendRegister")
        setContentView(R.layout.activity_login)
    }
}