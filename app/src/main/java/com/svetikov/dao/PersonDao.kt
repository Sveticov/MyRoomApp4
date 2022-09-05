package com.svetikov.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.svetikov.model.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    suspend fun getAll(): List<Person>

    @Insert
    suspend fun insertPerson(person: Person)
}