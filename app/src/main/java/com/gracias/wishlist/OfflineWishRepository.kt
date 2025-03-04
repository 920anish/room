package com.gracias.wishlist

import kotlinx.coroutines.flow.Flow

class OfflineWishRepository(private val wishDao: WishDao) :WishRepository {

    override suspend fun insertItem(wishItem: Wish) = wishDao.insert(wishItem)
    override  suspend fun updateItem(wishItem:Wish) = wishDao.update(wishItem)
    override suspend fun deleteItem(wishItem: Wish) = wishDao.delete(wishItem)
    override fun getItemStream(id:Long): Flow<Wish> = wishDao.getItem(id)
    override fun getAllStream(): Flow<List<Wish>> = wishDao.getAll()

}