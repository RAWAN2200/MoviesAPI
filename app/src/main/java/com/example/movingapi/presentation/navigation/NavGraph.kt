package com.example.movingapi.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.movingapi.presentation.Screen.onBoarding.OnBoardingScreen
import com.example.movingapi.presentation.Screen.onBoarding.OnBoardingViewModel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.movingapi.presentation.Screen.popular.PopularMoviesViewModel
import com.example.movingapi.presentation.Screen.popular.filmsScreen

@Composable
fun MovieNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = onBoardingViewModel.startDestination
    )
    {
        composable(Screens.OnBoarding.route) {
            OnBoardingScreen(onBoardingViewModel, navController)
        }
        composable(Screens.PopularMovie.route) {
            val viewModel = hiltViewModel<PopularMoviesViewModel>()
            filmsScreen(navController, viewModel.popularMoviesState)
        }
        composable(Screens.Search.route) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)) {

            }
        }
        composable(Screens.Profile.route) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)) {

            }
        }

    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}



//
//sealed class Screens(val route: String) {
//   data  object OnBoarding : Screens("onBoardingScreen1")
//    data object PopularMovie : Screens("filmsScreen")
//
//}
//
//
//@Composable
//fun NavGraph(
//    navController: NavHostController = rememberNavController()
//) {
//    val OnBoardingViewModel: OnBoardingViewModel = hiltViewModel()
//    NavHost(
//        navController = navController,
//        startDestination = OnBoardingViewModel.startDestination)
//    {
//        composable(Screens.OnBoarding.route){
//            OnBoardingScreen(OnBoardingViewModel,navController)
//        }
//        composable(Screens.OnBoarding.route) {
//            val viewModel= hiltViewModel<PopularViewModel>()
//            filmsScreen(navController , viewModel.popularMoviesState)
//        }
//
//    }
//
//}
//
//
//fun NavOptionsBuilder.popUpToTop(navController: NavController){
//    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return){
//        inclusive = true
//    }
//}