package com.example.lookatxing.domain.github

import com.example.domain.GitHubResponseItem
import com.example.lookatxing.data.local.XingDao
import com.example.lookatxing.data.remote.XingService
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class GithubRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    private lateinit var mapper: GithubMapper

    @MockK
    private lateinit var xingService: XingService

    @MockK
    private lateinit var xingDao: XingDao

    private lateinit var githubRepository: GithubRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        githubRepository = GithubRepository(mapper, xingService, xingDao, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when get two elements from api should trigger dao insert two times only`() {
        runBlockingTest {
            coEvery {
                xingService.requestRepos(any())
            } returns listOf(GitHubResponseItem(), GitHubResponseItem())

            coEvery {
                xingDao.insert(any())
            } just Runs

            every {
                mapper.mapTo(any())
            } returns listOf(mockk(), mockk())

            githubRepository.getElementsFromApi(0)

            coVerify(exactly = 1) {
                xingService.requestRepos(any())
            }

            coVerify(exactly = 2) {
                xingDao.insert(any())
            }
            verify(exactly = 1) {
                mapper.mapTo(any())
            }
        }
    }

}