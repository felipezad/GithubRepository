package com.example.lookatxing.data.remote

import com.example.domain.GitHubResponse
import retrofit2.http.GET

interface XingService {

    @GET
    suspend fun requestRepos(): GitHubResponse
}