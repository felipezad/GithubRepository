package com.example.lookatxing.domain.github

import com.example.domain.GitHubResponse
import com.example.lookatxing.domain.Mapper
import javax.inject.Inject

class GithubMapper @Inject constructor() : Mapper<GitHubResponse, List<Github>> {

    override fun mapTo(from: GitHubResponse): List<Github> {
        val gitHubList = mutableListOf<Github>()
        from.mapTo(gitHubList, { itemResponse ->
            Github(
                id = itemResponse.id,
                nameRepository = itemResponse.name,
                description = itemResponse.description,
                owner = itemResponse.fullName,
                ownerAvatarURL = itemResponse.owner.avatarUrl,
                fork = itemResponse.fork
            )
        })
        return gitHubList
    }
}