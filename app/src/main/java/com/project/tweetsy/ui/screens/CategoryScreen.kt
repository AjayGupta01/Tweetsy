package com.project.tweetsy.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.tweetsy.R
import com.project.tweetsy.viewmodels.TweetCategoryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun CategoryScreen(onClick:(category:String)->Unit) {

    val categoryViewModel: TweetCategoryViewModel = hiltViewModel()
    val categories = categoryViewModel.category.collectAsState()
    if (categories.value.isNotEmpty()){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.value) {
                CategoryItem(category = it.category,onClick)
            }
        }
    }
    else{
        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center){
            Text(text = "loading...", fontSize = 20.sp)
        }
    }
}


@Composable
fun CategoryItem(category: String, onClick: (category:String) -> Unit) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick(category)
            }
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        Text(
            text = category,
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 20.dp)
        )
    }
}