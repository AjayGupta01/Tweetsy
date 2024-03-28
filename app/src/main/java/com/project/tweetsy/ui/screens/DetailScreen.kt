package com.project.tweetsy.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.tweetsy.viewmodels.TweetCategoryViewModel
import com.project.tweetsy.viewmodels.TweetViewModel


@Composable
fun DetailScreen() {
    val tweetViewModel:TweetViewModel = hiltViewModel()
    val tweets = tweetViewModel.tweets.collectAsState()

    if (tweets.value.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center){
            Text(text = "loading...", fontSize = 20.sp)
        }
    }
    else{
        LazyColumn{
            items(tweets.value){
                TweetDetail(it.text)
            }
        }
    }


}

@Composable
fun TweetDetail(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(15.dp)
        )
    }
}