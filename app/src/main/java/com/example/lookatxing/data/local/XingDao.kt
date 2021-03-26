package com.example.lookatxing.data.local

import androidx.room.*
import com.example.lookatxing.domain.github.Github

@Dao
interface XingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: Github)

    @Delete
    suspend fun delete(hero: Github)

    @Query("SELECT * FROM ${Github.TABLE_NAME}")
    suspend fun getRepositories(): List<Github>
}