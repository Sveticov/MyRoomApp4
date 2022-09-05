package com.svetikov.mainscreen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.svetikov.PersonViewModel
import com.svetikov.database.AppDatabase
import com.svetikov.model.Person
import com.svetikov.ui.theme.MyRoomAppTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(personViewModel: PersonViewModel, db: AppDatabase) {
    var name by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }
    var showDataBase by remember {
        mutableStateOf(false)
    }
    val scope= rememberCoroutineScope()

    var data by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text("Room App", fontSize = 18.sp, modifier = Modifier.padding(top = 5.dp))
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier.padding(8.dp)
                    )
                }

            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Add Info", fontSize = 22.sp)
            OutlinedTextField(value = name, onValueChange = { name = it }, label = {
                Text("Name")
            })
            OutlinedTextField(value = age, onValueChange = { age = it }, label = {
                Text(text = "Age")
            })
            Button(onClick = {
                personViewModel.addPerson(name, age)
                scope.launch {
                    db.personDao().insertPerson(Person(name = name, age = age))
                    name = ""
                    age = ""
                }



            }) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(text = personViewModel._person.toString())
            Button(onClick = { showDataBase = !showDataBase }) {
                Text("Show all")
            }
            if (showDataBase){
                scope.launch {
              data = db.personDao().getAll().toString()
                }
                Text(data)
            }

        }

    }

}


//@Preview(showBackground = true)
//@Composable
//fun prevMainScreen() {
//    MyRoomAppTheme {
//        MainScreen(personViewModel)
//    }
//}