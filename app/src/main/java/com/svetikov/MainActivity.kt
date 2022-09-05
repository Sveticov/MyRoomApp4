package com.svetikov

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.svetikov.database.AppDatabase
import com.svetikov.mainscreen.MainScreen
import com.svetikov.ui.theme.MyRoomAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Room.databaseBuilder(
        this,
        AppDatabase::class.java, "database-person"
    ).fallbackToDestructiveMigration().build()
        val personViewModel:PersonViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            MyRoomAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(personViewModel,db)
                }
            }
        }
    }
}

