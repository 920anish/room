package com.gracias.wishlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WishViewModel : ViewModel() {

     private val _titleState =   mutableStateOf("")
     private val _descriptionState =   mutableStateOf("")

    val titleState : State<String> = _titleState
    val descriptionState : State<String> = _descriptionState


    fun getUpdatedTitle(newTitle:String) {
        _titleState.value = newTitle
    }
    fun getUpdatedDescription(newDescription: String) {
        _descriptionState.value= newDescription
    }

}