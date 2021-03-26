package com.example.domain

sealed class ActionResult<out R> {

    data class Success<out T>(val data: T) : ActionResult<T>()
    data class Error(val exception: Exception) : ActionResult<Nothing>()
    object Loading : ActionResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

val ActionResult<*>.succeeded
    get() = this is ActionResult.Success && data != null
