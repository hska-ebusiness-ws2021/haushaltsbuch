package com.example.haushaltsbuch.ui.charts

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.haushaltsbuch.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*

class PieChart : AppCompatActivity() {

    private lateinit var chart: com.github.mikephil.charting.charts.PieChart
    private var tf: Typeface? = null
    protected var tfLight: Typeface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)
        setSupportActionBar(findViewById(R.id.toolbar))
        tfLight = Typeface.createFromAsset(assets, "OpenSans-Light.ttf")
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }
        chart = findViewById<com.github.mikephil.charting.charts.PieChart>(R.id.chart1)
        title = "Diagramm"

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
        // enable rotation of the chart by touch
        chart.isRotationEnabled = true
        chart.isHighlightPerTapEnabled = true

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        // chart.setOnChartValueSelectedListener(this)


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
            "Einkaufen", "Haushalt", "Auto", "Urlaub", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J"
        )

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
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

        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
         data.setValueTypeface(tfLight)
        chart.data = data


        // undo all highlights

        // undo all highlights
        chart.highlightValues(null)

        chart.invalidate()
    }

    private fun generateCenterSpannableText(): SpannableString {
        val s = SpannableString("Haushalsbuch") // Name der App
        //        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
//        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(StyleSpan(Typeface.ITALIC), 0, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), 0, s.length, 0)
        return s
    }

}