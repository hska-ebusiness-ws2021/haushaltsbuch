package com.example.haushaltsbuch.ui.charts

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.haushaltsbuch.R
import com.example.haushaltsbuch.ui.home.ExpenseOverview
import com.example.haushaltsbuch.ui.home.HomeActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class PieChart : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var chart: com.github.mikephil.charting.charts.PieChart
    private var tf: Typeface? = null
    protected var tfLight: Typeface? = null
    private var drawer: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById<DrawerLayout>(R.id.drawer)
        val sidenav: NavigationView = findViewById(R.id.sidenav)
        sidenav.setNavigationItemSelectedListener(this)
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer?.addDrawerListener(toggle)
        toggle.syncState()

        tfLight = Typeface.createFromAsset(assets, "OpenSans-Light.ttf")

        chart = findViewById<com.github.mikephil.charting.charts.PieChart>(R.id.chart1)
        title = "Übersicht deiner Ausgaben"

        chart.setUsePercentValues(true)
        chart.description.isEnabled = false
        chart.setExtraOffsets(5f, 10f, 5f, 5f)

        chart.dragDecelerationFrictionCoef = 0.95f

        //chart.setCenterTextTypeface(tfLight)
        chart.centerText = generateCenterSpannableText()

        chart.isDrawHoleEnabled = true
        chart.setHoleColor(Color.WHITE)

        chart.setTransparentCircleColor(Color.WHITE)
        chart.setTransparentCircleAlpha(100)

        chart.holeRadius = 45f
        chart.transparentCircleRadius = 48f

        chart.setDrawCenterText(true)

        chart.rotationAngle = 0f
        // enable rotation of the chart by touch
        chart.isRotationEnabled = true
        chart.isHighlightPerTapEnabled = true

        chart.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);;


        // entry label styling
        chart.setEntryLabelColor(Color.BLACK)
        //chart.setEntryLabelTypeface(tfRegular)
        chart.setEntryLabelTextSize(12f)

        setData(10, 10f)

    }


    private fun setData(count: Int, range: Float) {
        val entries = ArrayList<PieEntry>()

        val parties = arrayOf(
            "Lebensmittel", "Haushalt", "Miete", "Transport", "Ausgehen", "Kleidung", "Studium", "Urlaub"
        )

        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    i.toFloat() * 10, // ((Math.random() * range) + range / 5),
                    parties.get(i % parties.size)  //modulo
                )
            )
        }
        val dataSet = PieDataSet(entries, "Kategorien")

        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        // dataSet.iconsOffset = MPPointF(0, 0)
        dataSet.selectionShift = 5f


        // add a lot of colors
        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
         data.setValueTypeface(tfLight)
        chart.data = data

        // undo all highlights
        chart.highlightValues(null)

        chart.invalidate()
    }

    private fun generateCenterSpannableText(): SpannableString {
        val s = SpannableString("Haushalsbuch") // Name der App
        s.setSpan(StyleSpan(Typeface.ITALIC), 0, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), 0, s.length, 0)
        return s
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // add the routing for remaining elements
            R.id.nav_statistics -> {startActivity(Intent(this, PieChart::class.java))}
            R.id.nav_home -> {startActivity(Intent(this, HomeActivity::class.java))
            }
        }
        return true
    }

}