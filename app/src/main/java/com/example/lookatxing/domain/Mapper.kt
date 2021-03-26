package com.example.lookatxing.domain

interface Mapper<in K, out T> {
    fun mapTo(from: K): T

    fun to(from: List<K>): List<T> {
        return from.map { mapTo(it) }
    }
}