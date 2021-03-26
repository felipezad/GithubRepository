package com.example.lookatxing.data.remote

import com.example.domain.GitHubResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface XingService {

    @GET("repos")
    suspend fun requestRepos(@Query("page") page: Int = 1): List<GitHubResponseItem>
}