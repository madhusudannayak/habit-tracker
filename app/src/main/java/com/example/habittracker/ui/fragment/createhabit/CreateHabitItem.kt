package com.example.habittracker.ui.fragment.createhabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.data.models.Habits
import com.example.habittracker.ui.viewmodels.HabitsViewModel
import com.example.habittracker.utils.Calculation
import kotlinx.android.synthetic.main.fragment_create_habit_item.*
import java.util.*


class CreateHabitItem : Fragment(R.layout.fragment_create_habit_item),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var title = ""
    private var description = ""
    private var drawableSelected = 0
    private var timeStamp = ""

    private var day = 0
    private var months = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var habitViewModel: HabitsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        habitViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        btn_confirm.setOnClickListener {
            addHabitToDB()
        }
        pickDateAndTime()

        drawableSelected()
    }

    private fun pickDateAndTime() {
        btn_pickDate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, months, day).show()
        }
        btn_pickTime.setOnClickListener {
            getTimeCalendar()
            TimePickerDialog(context, this, hour, minute, true).show()
        }
    }

    private fun drawableSelected() {
        iv_fastFoodSelected.setOnClickListener {
            iv_fastFoodSelected.isSelected = !iv_fastFoodSelected.isSelected
            drawableSelected = R.drawable.ic_fastfood

            //de-select the other options when we pick an image
            iv_smokingSelected.isSelected = false
            iv_teaSelected.isSelected = false
        }

        iv_smokingSelected.setOnClickListener {
            iv_smokingSelected.isSelected = !iv_smokingSelected.isSelected
            drawableSelected = R.drawable.ic_smoking2

            //de-select the other options when we pick an image
            iv_fastFoodSelected.isSelected = false
            iv_teaSelected.isSelected = false
        }

        iv_teaSelected.setOnClickListener {
            iv_teaSelected.isSelected = !iv_teaSelected.isSelected
            drawableSelected = R.drawable.ic_tea

            //de-select the other options when we pick an image
            iv_fastFoodSelected.isSelected = false
            iv_smokingSelected.isSelected = false
        }


    }

    private fun addHabitToDB() {
        title = et_habitTitle.text.toString()
        description = et_habitDescription.text.toString()
        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty() || drawableSelected == 0)) {
            val habit = Habits(0, title, description, timeStamp, drawableSelected)
            habitViewModel.addHabit(habit)
            Toast.makeText(context, "Habit created successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createHabitItem_to_habitListFragment)
        } else {
            Toast.makeText(context, "Please fill all the field", Toast.LENGTH_SHORT).show()
        }


    }

    private fun getTimeCalendar() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        months = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime = Calculation.cleanTime(hourOfDay, minute)
        tv_timeSelected.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculation.cleanDate(dayOfMonth, month, year)
        tv_dateSelected.text = "Date: $cleanDate"
    }


}