package com.example.lookatxing.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lookatxing.domain.github.Github

@Database(
    entities = [Github::class],
    version = 1
)
abstract class XingDatabase : RoomDatabase() {

    abstract fun heroDao(): XingDao

    companion object {
        private const val DB_NAME = "xing_database"

        @Volatile
        private var INSTANCE: XingDatabase? = null

        fun getInstance(context: Context): XingDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    XingDatabase::class.java,
                    DB_NAME
                )
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}