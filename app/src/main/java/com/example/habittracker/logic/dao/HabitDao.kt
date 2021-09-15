package com.example.habittracker.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habittracker.data.models.Habits

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHabit(habits: Habits)

    @Update
    suspend fun updateHabit(habits: Habits)

    @Delete
    suspend fun deleteHabit(habits: Habits)

    @Query("SELECT * FROM habit_table ORDER BY id DESC")
    fun getAllHabits(): LiveData<List<Habits>>

    @Query("DELETE FROM habit_table")
    suspend fun deleteAll()
}