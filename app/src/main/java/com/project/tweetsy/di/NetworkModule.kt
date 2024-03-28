package com.project.tweetsy.di

import com.project.tweetsy.api.TweetsyAPI
import com.project.tweetsy.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun tweetsyAPI(retrofit: Retrofit):TweetsyAPI{
        return retrofit.create(TweetsyAPI::class.java)
    }
}