package com.example.movingapi.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.movingapi.presentation.Screen.Details.MovieDetailViewModel
import com.example.movingapi.presentation.Screen.Details.MovieDetailsScreen
import com.example.movingapi.presentation.Screen.Profile.ProfileScreen
import com.example.movingapi.presentation.Screen.Profile.UserProfileViewModel
import com.example.movingapi.presentation.Screen.Search.SearchScreen
import com.example.movingapi.presentation.Screen.Search.SearchViewModel
import com.example.movingapi.presentation.Screen.onBoarding.OnBoardingScreen
import com.example.movingapi.presentation.Screen.onBoarding.OnBoardingViewModel
import com.example.movingapi.presentation.Screen.popular.PopularMoviesViewModel
import com.example.movingapi.presentation.Screen.popular.filmsScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    val OnBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = OnBoardingViewModel.startDestination)
    {
        composable(Screens.OnBoarding.route){
            OnBoardingScreen(OnBoardingViewModel,navController)
        }
        composable(Screens.PopularMovie.route) {
            val viewModel= hiltViewModel<PopularMoviesViewModel>()
            filmsScreen(navController , viewModel.popularMoviesState)
        }
        composable(Screens.Profile.route){
            val viewModel = hiltViewModel<UserProfileViewModel>()
           ProfileScreen(viewModel, navController)
        }

        composable("${Screens.MovieDetail.route}/{id}", arguments = listOf(
            navArgument("id"){
                type= NavType.IntType
            }
        )){
            val viewModel = hiltViewModel<MovieDetailViewModel>()
            MovieDetailsScreen(viewModel=viewModel, int = it.arguments?.getInt("id"),)
        }

        composable(Screens.Search.route) {
            val viewModel= hiltViewModel<SearchViewModel>()
            SearchScreen(navController , viewModel.moviesState){
                viewModel.searchIntMovies(it)
            }
        }

    }

}
 fun NavOptionsBuilder.popUpToTop(navController: NavController) {
  popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
    saveState = true
      inclusive = true
  }
 }
