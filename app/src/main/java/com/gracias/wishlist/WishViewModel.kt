package com.gracias.wishlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WishViewModel : ViewModel() {

    //for add screen
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

    //for edit screen
    private val _editTitleState = mutableStateOf("")
    private val _editDescriptionState = mutableStateOf("")

    val editTitleState : State<String> = _editTitleState
    val editDescriptionState:State<String> = _editDescriptionState

    fun getEditedTitle(newTitle:String) {
        _editTitleState.value = newTitle
    }
    fun getEditedDescription(newDescription: String) {
        _editDescriptionState.value= newDescription
    }



}