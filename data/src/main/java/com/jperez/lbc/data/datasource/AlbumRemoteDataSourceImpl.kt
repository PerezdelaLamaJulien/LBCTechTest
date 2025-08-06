package com.jperez.lbc.data.datasource

import com.jperez.lbc.data.api.ApiClient
import com.jperez.lbc.data.model.AlbumATO
import org.koin.java.KoinJavaComponent.inject

class AlbumRemoteDataSourceImpl : AlbumRemoteDataSource {
    private val apiClient: ApiClient by inject(ApiClient::class.java)

    override suspend fun getAlbums(): List<AlbumATO> {
        val response = apiClient.getAlbums()
        return response
    }
}
