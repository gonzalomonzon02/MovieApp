package com.gonzalomonzon02.movieapp.domain.repository

sealed class Source {
    data object Remote : Source()
    data object Local : Source()
} 