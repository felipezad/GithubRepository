package com.example.lookatxing.ui.main

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.ActionResult
import com.example.lookatxing.domain.github.GetGitHubListLocalUseCase
import com.example.lookatxing.domain.github.GetGitHubListUseCase
import com.example.lookatxing.domain.github.Github
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getGitHubListLocalUseCase: GetGitHubListLocalUseCase

    @MockK
    lateinit var getGitHubListUseCase: GetGitHubListUseCase

    private lateinit var subject: MainViewModel

    private lateinit var observerGithubRepositories: Observer<List<Github>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        subject = MainViewModel(getGitHubListUseCase, getGitHubListLocalUseCase)
        observerGithubRepositories = Observer { }
        subject.gitHubRepository.observeForever(observerGithubRepositories)
        mockkStatic(Log::class)
        setupLogMock()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        subject.gitHubRepository.removeObserver(observerGithubRepositories)
    }


    @Test
    fun `When Api Call is made and returns a successful list then should match with the value send to UI`() {
        runBlockingTest {
            val expectedList: List<Github> = generateList()
            setupSuccessfulUseCases(expectedList)

            subject.retrieveListGitHub(0)

            subject.gitHubRepository

            val actualList = subject.gitHubRepository.value
            assertNotNull(actualList)
            assertEquals(expectedList.size, actualList?.size)
            assertEquals(expectedList[0].id, actualList?.get(0)?.id)
        }
    }

    @Test
    fun `When Room Call is made and returns a successful list then should match with the value send to UI`() {
        runBlockingTest {
            val expectedList: List<Github> = generateList()
            setupSuccessfulUseCases(expectedList)

            subject.retrieveListFromRoom()

            subject.gitHubRepository

            val actualList = subject.gitHubRepository.value
            assertNotNull(actualList)
            assertEquals(expectedList.size, actualList?.size)
            assertEquals(expectedList[0].id, actualList?.get(0)?.id)
        }
    }

    private fun setupSuccessfulUseCases(answerGitHub: List<Github>) {
        coEvery {
            getGitHubListLocalUseCase.execute()
        } returns ActionResult.Success(answerGitHub)
        coEvery {
            getGitHubListUseCase.execute(any())
        } returns ActionResult.Success(answerGitHub)
    }

    private fun setupLogMock() {
        every {
            Log.d(any(), any())
        } returns 0
    }

    private fun generateList(): List<Github> {
        val githubOne = Github(1, "", "", "", "", false)
        val githubTwo = Github(2, "", "", "", "", false)
        return listOf(githubOne, githubTwo)
    }

}