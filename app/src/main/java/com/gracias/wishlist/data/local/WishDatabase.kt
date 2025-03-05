package com.gracias.wishlist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gracias.wishlist.data.local.entities.Wish

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false,
)

abstract class WishDatabase: RoomDatabase() {
    abstract fun wishDao(): WishDao

}