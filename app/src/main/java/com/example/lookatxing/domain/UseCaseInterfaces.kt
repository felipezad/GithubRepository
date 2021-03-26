package com.example.lookatxing.domain

import ActionResult

interface UseCaseConsumerProducer<in T, out K : Any> : UseCase {
    fun execute(param: T): ActionResult<K>
}

interface UseCaseProducer<out K : Any> : UseCase {
    fun execute(): ActionResult<K>
}

interface UseCase {
    val className: String
}