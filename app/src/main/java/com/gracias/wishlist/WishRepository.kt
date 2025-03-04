package com.gracias.wishlist

import kotlinx.coroutines.flow.Flow

interface WishRepository {

    suspend fun insertItem(wishItem: Wish)
    suspend fun updateItem(wishItem:Wish)
    suspend fun deleteItem(wishItem: Wish)
    fun getItemStream(id:Long): Flow<Wish>
    fun getAllStream():Flow<List<Wish>>

}