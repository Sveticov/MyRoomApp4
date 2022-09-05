package com.svetikov

import androidx.lifecycle.ViewModel
import com.svetikov.model.Person

class PersonViewModel:ViewModel() {
    val _person = Person()

    fun addPerson(name:String,age:String){
        _person.name=name
        _person.age=age
    }
}