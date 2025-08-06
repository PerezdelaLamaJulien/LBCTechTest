package com.jperez.lbc.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jperez.lbc.data.model.AlbumATO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val BASE_URL = "https://static.leboncoin.fr/"

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
class ApiClient {
    private val retrofitService: AlbumApiService by lazy {
        retrofit.create(AlbumApiService::class.java)
    }

    suspend fun getAlbums(): List<AlbumATO> {
        return retrofitService.getAlbums()
    }
}