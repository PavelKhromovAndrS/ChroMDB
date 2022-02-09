package com.example.chromdb.model.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chromdb.App


@androidx.room.Database(
    entities = [
        HistoryEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        private const val DB_NAME = "add_database.db"

        val db: Database by lazy {
            Room.databaseBuilder(
                App.appInstance,
                Database::class.java,
                DB_NAME
            ).build()
        }
    }
}