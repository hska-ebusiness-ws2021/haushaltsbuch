package com.example.haushaltsbuch.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.haushaltsbuch.R
import com.example.haushaltsbuch.ui.addeditexpense.AddEditExpense

class HomeFragement: Fragment(R.layout.content_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = view.findViewById(R.id.buttonAusgaben)
        button.setOnClickListener{
            val intent = Intent(activity, AddEditExpense::class.java)
            intent.putExtra(AddEditExpense.IS_EINNAHMEN, false)
            startActivity(intent)
        }

        val buttonEinnahmen: Button = view.findViewById(R.id.button2)
        buttonEinnahmen.setOnClickListener{
            val intent = Intent(activity, AddEditExpense::class.java)
            intent.putExtra(AddEditExpense.IS_EINNAHMEN, true)
            startActivity(intent)
        }
    }
}