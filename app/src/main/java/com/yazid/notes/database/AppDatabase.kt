package com.yazid.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yazid.notes.model.note.Note
import com.yazid.notes.model.note.NoteDao

@Database(
    entities = [Note::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, context.packageName)
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance
            return instance
        }
    }
}