package com.gracias.wishlist.data.repository

import com.gracias.wishlist.data.local.entities.Wish
import com.gracias.wishlist.data.local.WishDao
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class OfflineWishRepository @Inject constructor(
    private val wishDao: WishDao
) : WishRepository {
    override suspend fun insertItem(wishItem: Wish) = wishDao.insert(wishItem)
    override suspend fun updateItem(wishItem: Wish) = wishDao.update(wishItem)
    override suspend fun deleteItem(wishItem: Wish) = wishDao.delete(wishItem)
    override fun getItemStream(id: Long): Flow<Wish> = wishDao.getItem(id)
    override fun getAllStream(): Flow<List<Wish>> = wishDao.getAll()

}
