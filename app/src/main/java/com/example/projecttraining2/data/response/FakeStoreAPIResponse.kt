package com.example.projecttraining2.data.response

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class FakeStoreAPIResponse(

	@field:SerializedName("FakeStoreAPIResponse")
	val fakeStoreAPIResponse: List<FakeStoreAPIResponseItem?>? = null
)


data class FakeStoreAPIResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: Any? = null,

	@field:SerializedName("rating")
	val rating: Rating? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

)

data class Rating(

	@field:SerializedName("rate")
	val rate: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null
)
