package com.example.lookatxing.domain.github

import com.example.domain.ActionResult
import com.example.lookatxing.data.local.XingDao
import com.example.lookatxing.data.remote.XingService
import com.example.lookatxing.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val mapper: GithubMapper,
    private val xingService: XingService,
    private val xingDao: XingDao
) : Repository<Github> {

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
            withContext(Dispatchers.IO) { value.forEach { xingDao.insert(it) } }
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