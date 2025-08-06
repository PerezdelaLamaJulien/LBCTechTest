package com.jperez.lbc.feature.di

import com.jperez.lbc.feature.mapper.AlbumListItemUIMapper
import com.jperez.lbc.feature.viewmodel.AlbumListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var featureKoinModule = module {
    factory<AlbumListItemUIMapper> {
        AlbumListItemUIMapper()
    }
    viewModel<AlbumListViewModel> {
        AlbumListViewModel()
    }
}