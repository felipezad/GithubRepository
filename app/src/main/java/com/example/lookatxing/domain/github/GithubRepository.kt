package com.example.lookatxing.domain.github

import com.example.domain.ActionResult
import com.example.lookatxing.data.local.XingDao
import com.example.lookatxing.data.remote.XingService
import com.example.lookatxing.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepository(
    private val mapper: GithubMapper,
    private val xingService: XingService,
    private val xingDao: XingDao,
    private val dispatcherThread: CoroutineDispatcher = Dispatchers.IO
) : Repository<Github> {

    @Inject
    constructor(
        mapper: GithubMapper,
        xingService: XingService,
        xingDao: XingDao
    ) : this(mapper, xingService, xingDao, Dispatchers.IO)

    override suspend fun insertDataIntoRoom(data: Github): ActionResult<Boolean> {
        return try {
            xingDao.insert(data)
            ActionResult.Success(true)
        } catch (e: Exception) {
            ActionResult.Success(false)
        }
    }

    override suspend fun getElementsFromApi(page: Int): ActionResult<List<Github>> {
        return try {
            val value: List<Github> = mapper.mapTo(xingService.requestRepos())
            withContext(dispatcherThread) { value.forEach { insertDataIntoRoom(it) } }
            ActionResult.Success(value)
        } catch (e: Exception) {
            ActionResult.Error(e)
        }
    }

    override suspend fun getElementsFromDatabase(): ActionResult<List<Github>> {
        return try {
            val value: List<Github> = xingDao.getRepositories()
            ActionResult.Success(value)
        } catch (e: Exception) {
            ActionResult.Error(e)
        }
    }
}