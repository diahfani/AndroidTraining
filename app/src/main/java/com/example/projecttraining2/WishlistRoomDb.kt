package com.example.projecttraining2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wishlist::class], version = 2)
abstract class WishlistRoomDb : RoomDatabase() {

    abstract fun wishlistDao(): WishlistDao

    companion object {
        @Volatile
        private var INSTANCE: WishlistRoomDb? = null

        @JvmStatic
        fun getDatabase(context: Context): WishlistRoomDb {
            if (INSTANCE == null) {
                synchronized(WishlistRoomDb::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        WishlistRoomDb::class.java, "wishlist_db")
                        .build()
                }
            }
            return INSTANCE as WishlistRoomDb
        }
    }
}