package com.example.lookatxing.di

import android.app.Application
import com.bumptech.glide.Glide
import com.example.lookatxing.BuildConfig
import com.example.lookatxing.data.local.XingDatabase
import com.example.lookatxing.data.remote.XingService
import com.example.util.WhenNullReturnEmptyFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): XingService {
        val moshi = Moshi.Builder()
            .add(WhenNullReturnEmptyFactory)
            .addLast(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(XingService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRequestManagerGlide(application: Application) = Glide.with(application)

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = XingDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideHeroDao(database: XingDatabase) = database.xingDao()
}