package com.example.lookatxing.data.remote

import com.example.domain.GitHubResponseItem
import retrofit2.http.GET

interface XingService {

    @GET("repos")
    suspend fun requestRepos(): List<GitHubResponseItem>
}