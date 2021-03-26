package com.example.lookatxing.domain

import com.example.domain.ActionResult

interface UseCaseConsumerProducer<in T, out K : Any> : UseCase {
    suspend fun execute(param: T): ActionResult<K>
}

interface UseCaseProducer<out K : Any> : UseCase {
    suspend fun execute(): ActionResult<K>
}

interface UseCase {
    val className: String
}
