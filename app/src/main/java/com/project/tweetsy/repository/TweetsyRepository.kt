package com.project.tweetsy.repository

import com.project.tweetsy.api.TweetsyAPI
import com.project.tweetsy.models.Category
import com.project.tweetsy.models.TweetFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetsyRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val category:StateFlow<List<Category>>
        get() = _categories

    private val _tweets =  MutableStateFlow<List<TweetFormat>>(emptyList())
    val tweets : StateFlow<List<TweetFormat>>
        get() = _tweets


    suspend fun getTweetCategories(){
        val response = tweetsyAPI.getCategories()
        if (response.isSuccessful && response.body()!=null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category:String){
        val response = tweetsyAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body()!=null){
            _tweets.emit(response.body()!!)
        }
    }
}


