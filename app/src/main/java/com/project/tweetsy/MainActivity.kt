package com.project.tweetsy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.tweetsy.api.TweetsyAPI
import com.project.tweetsy.models.Category
import com.project.tweetsy.ui.screens.CategoryScreen
import com.project.tweetsy.ui.screens.DetailScreen
import com.project.tweetsy.ui.screens.Screens
import com.project.tweetsy.ui.theme.TweetsyTheme
import com.project.tweetsy.viewmodels.TweetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = getString(R.string.tweetsy))},
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Black,
                            titleContentColor = Color.White,
                            navigationIconContentColor = Color.White),
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                            }
                        }
                    )
                }
            ) {
                Box(modifier = Modifier.padding(it)){
                    TweetsyApp()
                }
            }

        }
    }
}

@Composable
fun TweetsyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.CATEGORY.name ){

        composable(route = Screens.CATEGORY.name){
            CategoryScreen{
                navController.navigate("${Screens.DETAIL.name}/$it")
            }
        }

        composable(route = "${Screens.DETAIL.name}/{category}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType
            }
        )){
            DetailScreen()
        }
    }

}
