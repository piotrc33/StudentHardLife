package com.example.studenthardlife

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        ProblemList::class,
        Problem::class
    ],
    version = 1, exportSchema = false
)
abstract class ProblemListDatabase : RoomDatabase() {

    abstract fun problemListDao(): ProblemListDao
    abstract fun problemDao(): ProblemDao

    companion object {
        @Volatile
        private var INSTANCE: ProblemListDatabase? = null

        fun getDatabase(context: Context): ProblemListDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProblemListDatabase::class.java,
                    "problem_list_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}