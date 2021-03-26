package com.example.lookatxing.domain.github

import android.os.Parcelable
import androidx.room.Entity
import com.example.lookatxing.domain.github.Github.Companion.TABLE_NAME

import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = TABLE_NAME)
data class Github(
    val nameRepository: String,
    val description: String,
    val owner: String
) : Parcelable {
    companion object {
        const val TABLE_NAME = "github"
    }
}
