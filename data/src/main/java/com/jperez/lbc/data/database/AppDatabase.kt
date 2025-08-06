package com.jperez.lbc.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jperez.lbc.data.model.entity.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}