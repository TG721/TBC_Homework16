package com.example.tbc_homework16.network

import com.example.tbc_homework16.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private val retrofitBuilder by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    fun getUserInfo(): UserApi = retrofitBuilder.create(UserApi::class.java)
}