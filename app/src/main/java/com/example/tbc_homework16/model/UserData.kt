package com.example.tbc_homework16.model

import com.squareup.moshi.Json

data class UserData(
    val data: List<Data>,
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    val text: String,
    val url: String,
    val total: Int?,
    @Json(name = "total_pages")
    val totalPages: Int
)

{
    data class Data(
        val avatar: String,
        val email: String?,
        @Json(name = "first_name")
        val firstName: String,
        val id: Int,
        @Json(name = "last_name")
        val lastName: String
    )
}


