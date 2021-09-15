package com.example.habittracker.logic.repository

import androidx.lifecycle.LiveData
import com.example.habittracker.data.models.Habits
import com.example.habittracker.logic.dao.HabitDao

class HabitRepository(val habitDao: HabitDao) {
    val getAllHabits: LiveData<List<Habits>> = habitDao.getAllHabits()


    suspend fun addHabit(habits: Habits) {
        habitDao.addHabit(habits)
    }

    suspend fun updateHabit(habits: Habits) {
        habitDao.updateHabit(habits)
    }

    suspend fun deleteHabit(habits: Habits) {
        habitDao.deleteHabit(habits)
    }

    suspend fun deleteAllHabits() {
        habitDao.deleteAll()
    }
}