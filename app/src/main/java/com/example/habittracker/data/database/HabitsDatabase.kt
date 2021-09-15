package com.example.habittracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittracker.data.models.Habits
import com.example.habittracker.logic.dao.HabitDao

@Database(entities = [Habits::class], version = 1, exportSchema = false)
abstract class HabitsDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: HabitsDatabase? = null
        fun getDatabase(context: Context) : HabitsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instant = Room.databaseBuilder(
                        context.applicationContext,
                        HabitsDatabase::class.java,
                        "habit_table"
                ).build()
                INSTANCE =instant
                return  instant
            }
        }

    }
}