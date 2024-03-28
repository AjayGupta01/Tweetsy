package com.project.tweetsy.api

import com.project.tweetsy.models.Category
import com.project.tweetsy.models.TweetFormat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TweetsyAPI {

    @GET("/v3/b/65c12a3a1f5677401f2b422e?meta=false")
    suspend fun getCategories():Response<List<Category>>

    @GET("/v3/b/65c12486dc74654018a0b076?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category:String):Response<List<TweetFormat>>
}