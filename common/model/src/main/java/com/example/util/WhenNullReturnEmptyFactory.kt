package com.example.util

import com.example.annotations.WhenNullReturnEmpty
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.internal.Util.isAnnotationPresent
import java.lang.reflect.Type

object WhenNullReturnEmptyFactory : JsonAdapter.Factory {

    val annotationClass = WhenNullReturnEmpty::class.java

    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        if (annotations containsAssignable annotationClass) return Adapter
        return null
    }

    private object Adapter : JsonAdapter<String>() {
        override fun fromJson(reader: JsonReader): String? {
            with(reader) {
                return when (peek()) {
                    JsonReader.Token.NULL -> {
                        nextNull<Any>()
                        ""
                    }
                    else -> {
                        nextString()
                    }
                }
            }
        }

        override fun toJson(writer: JsonWriter, value: String?) {
            writer.value(value.toString())
        }
    }
}

private infix fun Set<Annotation>.containsAssignable(clazz: Class<out Annotation>): Boolean {
    return isAnnotationPresent(this, clazz)
}