package com.example.projecttraining2

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String?,
    val photo: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {

    }

    companion object : Parceler<Product> {
        override fun Product.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(photo)
        }

        override fun create(parcel: Parcel): Product {
            return Product(parcel)
        }
    }
}
