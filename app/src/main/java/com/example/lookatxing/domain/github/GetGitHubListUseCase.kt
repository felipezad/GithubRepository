package com.example.lookatxing.domain.github

import com.example.domain.ActionResult
import com.example.lookatxing.domain.UseCaseConsumerProducer
import javax.inject.Inject

class GetGitHubListUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) : UseCaseConsumerProducer<Int, List<Github>> {

    override suspend fun execute(param: Int): ActionResult<List<Github>> {
        return githubRepository.getElementsFromApi(page = param)
    }

    override val className: String = GetGitHubListUseCase::class.simpleName.orEmpty()
}