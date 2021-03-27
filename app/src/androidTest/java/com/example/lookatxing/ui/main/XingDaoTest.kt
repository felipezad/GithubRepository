package com.example.lookatxing.ui.main

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lookatxing.data.local.XingDatabase
import com.example.lookatxing.domain.github.Github
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class XingDaoTest {

    private lateinit var databaseMock: XingDatabase

    @Before
    fun setUp() {
        databaseMock = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            XingDatabase::class.java
        ).build()
    }


    @After
    fun tearDown() {
        databaseMock.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertGithubAndSelect() = runBlocking {
        val github = Github(1, "mock", "mock", "mock", "mock", false)
        databaseMock.xingDao().insert(github)

        val githubList: Github = databaseMock.xingDao().getRepositories().first()

        assertEquals(githubList, (listOf(github)).first())
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertGithubAndDelete() = runBlocking {
        val github = Github(1, "mock", "mock", "mock", "mock", false)
        databaseMock.xingDao().insert(github)
        databaseMock.xingDao().delete(github)

        val githubList = databaseMock.xingDao().getRepositories().firstOrNull()

        assertNull(githubList)
    }
}