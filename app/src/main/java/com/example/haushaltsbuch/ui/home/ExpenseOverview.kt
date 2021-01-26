package com.example.haushaltsbuch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.haushaltsbuch.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExpenseOverview.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExpenseOverview : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        if(savedInstanceState == null){
            childFragmentManager.commit {
                setReorderingAllowed(true)
                add<ExpenseFragment>(R.id.expense_container)
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createExpense: FloatingActionButton = view.findViewById(R.id.overview_create_expense)
        createExpense.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<HomeFragement>(R.id.fragment_containter) }
        }

        val monthText: TextView = view.findViewById(R.id.overview_month)
        val cal = Calendar.getInstance()
        val month_date = SimpleDateFormat("MMMM", Locale.GERMAN)
        val month_name: String = month_date.format(cal.time)
        monthText.text = month_name
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExpenseOverview.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExpenseOverview().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}