package com.example.lookatxing.domain.github

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lookatxing.domain.github.Github.Companion.TABLE_NAME

import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = TABLE_NAME)
data class Github(
    @PrimaryKey
    val id: Int,
    val nameRepository: String,
    val description: String,
    val owner: String,
    val ownerAvatarURL: String,
    val fork: Boolean
) : Parcelable {
    companion object {
        const val TABLE_NAME = "github"
    }
}
