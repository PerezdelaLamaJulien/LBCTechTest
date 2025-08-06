package com.jperez.lbc.domain.di

import com.jperez.lbc.domain.mapper.AlbumMapper
import com.jperez.lbc.domain.usecase.GetAlbumsUseCase
import org.koin.dsl.module

var domainKoinModule = module {
    factory<AlbumMapper> {
        AlbumMapper()
    }
    factory<GetAlbumsUseCase> {
        GetAlbumsUseCase()
    }
}