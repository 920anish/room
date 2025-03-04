package com.gracias.wishlist

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Add

@Serializable
data class Edit(val id:Long)