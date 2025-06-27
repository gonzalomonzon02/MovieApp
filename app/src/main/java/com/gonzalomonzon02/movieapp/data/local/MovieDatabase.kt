package com.gonzalomonzon02.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    companion object {
        const val LOCAL_DB_NAME = "movies_db"
    }

    abstract fun movieDao(): MovieDao
} 