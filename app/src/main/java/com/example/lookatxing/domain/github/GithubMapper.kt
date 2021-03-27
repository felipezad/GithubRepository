package com.example.lookatxing.domain.github

import com.example.domain.GitHubResponseItem
import com.example.lookatxing.domain.Mapper
import javax.inject.Inject

class GithubMapper @Inject constructor() : Mapper<List<GitHubResponseItem>, List<Github>> {

    override fun mapTo(from: List<GitHubResponseItem>): List<Github> {
        val gitHubList = mutableListOf<Github>()
        from.mapTo(gitHubList, { itemResponse ->
            Github(
                id = itemResponse.id,
                nameRepository = itemResponse.name,
                description = itemResponse.description,
                owner = itemResponse.owner.login,
                ownerAvatarURL = itemResponse.owner.avatarUrl,
                fork = itemResponse.fork
            )
        })
        return gitHubList
    }
}