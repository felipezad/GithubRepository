package com.example.lookatxing.domain

import com.example.domain.ActionResult

interface Repository<T> {

    suspend fun insertDataIntoRoom(data: T): ActionResult<Boolean>

    suspend fun getElementsFromApi(page: Int): ActionResult<List<T>>

    suspend fun getElementsFromDatabase(): ActionResult<List<T>>
}
