package com.gracias.wishlist.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gracias.wishlist.data.local.entities.Wish
import com.gracias.wishlist.data.repository.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class WishViewModel @Inject constructor(
    private val wishRepository: WishRepository
) : ViewModel() {


    //for add screen  text field
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

    //for edit screen text field
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


    val allWishes : Flow<List<Wish>> = wishRepository.getAllStream()

    fun getWishById(id:Long) : Flow<Wish> = wishRepository.getItemStream(id)


    fun insertWish(wishItem : Wish)  {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.insertItem(wishItem)
        }
    }

    fun updateWish(wishItem: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateItem(wishItem)
        }
    }

    fun deleteWish(wishItem: Wish){
        viewModelScope.launch{
            wishRepository.deleteItem(wishItem)
        }
    }


    fun clearField() {
        _titleState.value = ""
        _descriptionState.value = ""
    }

}