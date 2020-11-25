package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "date"

class DatePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bundleDate = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = bundleDate

        val dateListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
                val resultDate: Date = GregorianCalendar(year, month, day, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE)).time
                targetFragment?.let { fragment -> (fragment as Callbacks).onDateSelected(resultDate) }
            }

        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDate = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), dateListener, initialYear, initialMonth, initialDate)
    }

    companion object {
        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}