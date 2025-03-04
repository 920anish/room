package com.gracias.wishlist

import android.app.Application

class WishlistApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        AppContainer.provide(this)
    }
}