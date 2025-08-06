package com.jperez.lbc.feature.model

sealed class ConnectivityState {
    object Available : ConnectivityState()
    object Unavailable : ConnectivityState()
}