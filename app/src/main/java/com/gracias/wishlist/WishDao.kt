package com.gracias.wishlist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wishItem: Wish)

    @Update
    suspend fun update(wishItem:Wish)

    @Delete
    suspend fun delete(wishItem: Wish)

    @Query("SELECT * FROM wishes WHERE id = :id")
    fun getItem(id:Long):Flow<Wish>

    @Query("SELECT * FROM wishes")
    fun getAll():Flow<List<Wish>>



}