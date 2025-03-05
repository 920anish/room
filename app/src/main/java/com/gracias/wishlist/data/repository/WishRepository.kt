package com.gracias.wishlist.data.repository

import com.gracias.wishlist.data.local.entities.Wish
import kotlinx.coroutines.flow.Flow

interface WishRepository {

    suspend fun insertItem(wishItem: Wish)
    suspend fun updateItem(wishItem: Wish)
    suspend fun deleteItem(wishItem: Wish)
    fun getItemStream(id:Long): Flow<Wish>
    fun getAllStream():Flow<List<Wish>>

}