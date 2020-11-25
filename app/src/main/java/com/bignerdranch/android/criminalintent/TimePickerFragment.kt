package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_TIME = "time"

class TimePickerFragment: DialogFragment(){

    interface CallBackTime{
        fun onTimeSelect(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bundleTime = arguments?.getSerializable(ARG_TIME) as Date
        val calendar = Calendar.getInstance()
        calendar.time = bundleTime

        val timeListener =
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hour: Int, minute: Int ->
                val resultDate: Date = GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour,minute ).time
                targetFragment?.let { fragment ->
                    (fragment as CallBackTime).onTimeSelect(resultDate)
                }
            }

        val initialHour = calendar.get(Calendar.HOUR)
        val initialMinute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(requireContext(), timeListener, initialHour, initialMinute, true)
    }

    companion object {
        fun newInstance(time: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, time)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}