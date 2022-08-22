package com.example.tbc_homework16.network

import com.example.tbc_homework16.model.UserData
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getPersonData(@Query("page") page: Int): UserData

}