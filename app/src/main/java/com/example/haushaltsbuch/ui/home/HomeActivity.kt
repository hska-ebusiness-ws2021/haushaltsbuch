package com.example.haushaltsbuch.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.haushaltsbuch.R
import com.example.haushaltsbuch.ui.addeditexpense.AddEditExpense
import com.example.haushaltsbuch.ui.charts.PieChart
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawer: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragement>(R.id.fragment_containter)
            }
        }
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar))
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById<DrawerLayout>(R.id.drawer)
        val sidenav: NavigationView = findViewById(R.id.sidenav)
        sidenav.setNavigationItemSelectedListener(this)
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer?.addDrawerListener(toggle)
        toggle.syncState()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // add the routing for remaining elements
            R.id.nav_statistics -> {startActivity(Intent(this, PieChart::class.java))}
            R.id.nav_home -> {supportFragmentManager.commit { replace<ExpenseOverview>(R.id.fragment_containter)
                setReorderingAllowed(true)
                addToBackStack("")
                }}
        }
        return true
    }
    override fun onBackPressed() {
        if (drawer?.isDrawerOpen(GravityCompat.START) == true){
            drawer?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}