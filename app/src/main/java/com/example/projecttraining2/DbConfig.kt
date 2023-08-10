package com.example.projecttraining2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Wishlist::class], version = 2)
abstract class DbConfig : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao
}