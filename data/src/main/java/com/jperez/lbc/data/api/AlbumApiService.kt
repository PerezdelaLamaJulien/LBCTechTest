package com.jperez.lbc.data.api

import com.jperez.lbc.data.model.AlbumATO
import retrofit2.http.GET

interface AlbumApiService {

    /**
     * Get a list of albums from the API.
     *
     * @return A list of [AlbumATO] objects.
     */
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumATO>
}