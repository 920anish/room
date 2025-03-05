package com.gracias.wishlist.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Add

@Serializable
data class Edit(val id:Long)