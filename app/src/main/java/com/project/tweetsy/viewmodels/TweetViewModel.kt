package com.project.tweetsy.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.tweetsy.models.Category
import com.project.tweetsy.models.TweetFormat
import com.project.tweetsy.repository.TweetsyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(
    private val repository: TweetsyRepository,
    private val stateHandle: SavedStateHandle) : ViewModel() {

    val tweets: StateFlow<List<TweetFormat>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            val category = stateHandle.get<String>("category")?:"Motivation"
            repository.getTweets(category)
        }
    }
}