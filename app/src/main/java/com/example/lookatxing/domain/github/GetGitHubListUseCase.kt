package com.example.lookatxing.domain.github

import com.example.domain.ActionResult
import com.example.lookatxing.domain.UseCaseProducer
import javax.inject.Inject

class GetGitHubListUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) : UseCaseProducer<List<Github>> {

    override suspend fun execute(): ActionResult<List<Github>> {
        return githubRepository.getElementsFromApi(5)
    }

    override val className: String = GetGitHubListUseCase::class.simpleName.orEmpty()
}