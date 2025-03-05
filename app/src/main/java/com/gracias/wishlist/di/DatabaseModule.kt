package com.gracias.wishlist.di

import android.content.Context
import androidx.room.Room
import com.gracias.wishlist.data.local.WishDao
import com.gracias.wishlist.data.local.WishDatabase
import com.gracias.wishlist.data.repository.OfflineWishRepository
import com.gracias.wishlist.data.repository.WishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WishDatabase {
        return Room.databaseBuilder(
            context,
            WishDatabase::class.java,
            "wish.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWishDao(database: WishDatabase): WishDao {
        return database.wishDao()
    }

    @Provides
    @Singleton
    fun provideWishRepository(wishDao: WishDao): WishRepository {
        return OfflineWishRepository(wishDao)
    }
}
