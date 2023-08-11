package com.example.projecttraining2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WishlistDao {
    @Query("SELECT * FROM  wishlist ORDER BY id DESC")
    fun getAllWishlist() : List<Wishlist>

    @Query("SELECT * FROM wishlist WHERE id = :id")
    fun getWishlistByID(id: Int) : Wishlist

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWishlist(product: Wishlist) : Long

    @Delete
    fun deleteWishlist(product: Wishlist)
}