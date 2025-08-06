package com.jperez.lbc.data.di

import androidx.room.Room
import com.jperez.lbc.data.api.ApiClient
import com.jperez.lbc.data.database.AppDatabase
import com.jperez.lbc.data.database.AlbumDao
import com.jperez.lbc.data.datasource.AlbumLocalDataSource
import com.jperez.lbc.data.datasource.AlbumLocalDataSourceImpl
import com.jperez.lbc.data.datasource.AlbumRemoteDataSource
import com.jperez.lbc.data.datasource.AlbumRemoteDataSourceImpl
import com.jperez.lbc.data.mapper.AlbumAtoEntityMapper
import com.jperez.lbc.data.repository.AlbumRepository
import com.jperez.lbc.data.repository.AlbumRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

var dataKoinModule = module {
    factory<ApiClient> {
        ApiClient()
    }
    factory<AlbumRemoteDataSource> {
        AlbumRemoteDataSourceImpl()
    }
    factory<AlbumLocalDataSource> {
        AlbumLocalDataSourceImpl()
    }
    factory<AlbumRepository> {
        AlbumRepositoryImpl()
    }
    factory<AlbumAtoEntityMapper> {
        AlbumAtoEntityMapper()
    }

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "lbc_db"
        ).build()
    }
    single<AlbumDao> {
        get<AppDatabase>().albumDao()
    }
}