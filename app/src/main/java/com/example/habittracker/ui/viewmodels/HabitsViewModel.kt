package com.example.habittracker.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.database.HabitsDatabase
import com.example.habittracker.data.models.Habits
import com.example.habittracker.logic.repository.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HabitRepository

    val getAllHabits: LiveData<List<Habits>>

    init {
        val habitDao = HabitsDatabase.getDatabase(application).habitDao()
        repository = HabitRepository(habitDao)
        getAllHabits = repository.getAllHabits
    }
    fun addHabit(habits: Habits){
        viewModelScope.launch(Dispatchers.IO) {
        repository.addHabit(habits)
        }
    }
    fun updateHabit(habits: Habits){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHabit(habits)
        }
    }
    fun deleteHabit(habits: Habits){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHabit(habits)
        }
    }
    fun deleteAllHabits(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllHabits()
        }
    }
}