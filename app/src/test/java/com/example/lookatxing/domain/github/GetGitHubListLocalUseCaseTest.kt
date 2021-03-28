package com.example.lookatxing.domain.github

import com.example.domain.ActionResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetGitHubListLocalUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mockGithubRepository: GithubRepository

    private lateinit var getGitHubListUseCase: GetGitHubListLocalUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        getGitHubListUseCase = GetGitHubListLocalUseCase(mockGithubRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when repository returns data then the result should be success`() {
        runBlockingTest {
            coEvery {
                mockGithubRepository.getElementsFromDatabase()
            } returns ActionResult.Success(mockk())

            val result = getGitHubListUseCase.execute()

            assertEquals(true, result is ActionResult.Success)
        }
    }

    @Test
    fun `when repository fails returns data then the result should be failure`() {
        runBlockingTest {
            coEvery {
                mockGithubRepository.getElementsFromDatabase()
            } returns ActionResult.Error(Exception())

            val result = getGitHubListUseCase.execute()

            assertEquals(true, result is ActionResult.Error)
        }
    }
}