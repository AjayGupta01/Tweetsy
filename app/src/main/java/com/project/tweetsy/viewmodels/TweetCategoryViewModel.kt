package com.project.tweetsy.viewmodels

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
class TweetCategoryViewModel @Inject constructor(private val repository: TweetsyRepository) : ViewModel() {

    val category: StateFlow<List<Category>>
        get() = repository.category

    init {
     viewModelScope.launch {
         repository.getTweetCategories()
     }
    }
}