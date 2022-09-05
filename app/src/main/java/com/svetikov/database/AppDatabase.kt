package com.svetikov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.svetikov.dao.PersonDao
import com.svetikov.model.Person

@Database(entities = [Person::class], version = 3)
abstract class AppDatabase:RoomDatabase() {
    abstract fun personDao():PersonDao
}