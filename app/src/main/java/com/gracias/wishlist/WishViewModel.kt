package com.gracias.wishlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val offlineWishRepository: OfflineWishRepository = AppContainer.offlineWishRepository) : ViewModel() {


    //TODO optimize this and remove code duplication

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


    val allWishes : Flow<List<Wish>> = offlineWishRepository.getAllStream()

    fun getWishById(id:Long) : Flow<Wish> = offlineWishRepository.getItemStream(id)


    fun insertWish(wishItem : Wish)  {
        viewModelScope.launch(Dispatchers.IO) {
            offlineWishRepository.insertItem(wishItem)
        }
    }

    fun updateWish(wishItem: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            offlineWishRepository.updateItem(wishItem)
        }
    }

    fun deleteWish(wishItem: Wish){
        viewModelScope.launch{
            offlineWishRepository.deleteItem(wishItem)
        }
    }


    fun clearField() {
        _titleState.value = ""
        _descriptionState.value = ""
    }

}