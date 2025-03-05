package com.gracias.wishlist

import android.content.Context
import androidx.room.Room

//TODO Inject DI with Hilt plus Structure dirs
object AppContainer {

    private lateinit var database: WishDatabase
    fun provide(context: Context) {

        if(!::database.isInitialized)
         database= Room.databaseBuilder(
                context = context,
                WishDatabase::class.java,
                "wish.db"
            ).build()

    }


    private val wishDao: WishDao by lazy {
        database.wishDao()
    }

    val offlineWishRepository by lazy {
        OfflineWishRepository(wishDao)
    }



}