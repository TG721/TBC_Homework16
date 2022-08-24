package com.example.tbc_homework16.model

import com.squareup.moshi.Json

data class UserData(
    val data: List<User>,
    val page: Int,
    @field:Json(name = "per_page")
    val perPage: Int,
    val text: String,
    val url: String,
    val total: Int?,
    @field:Json(name = "total_pages")
    val totalPages: Int
)

{
    data class User(
        val avatar: String,
        val email: String?,
        @field:Json(name = "first_name")
        val firstName: String,
        val id: Int,
        @field:Json(name = "last_name")
        val lastName: String
    )
}


