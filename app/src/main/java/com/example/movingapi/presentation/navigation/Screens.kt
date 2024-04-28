package com.example.movingapi.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screens(val route: String) {
    data object OnBoarding : Screens("onBoardingScreen1")
    data object PopularMovie : Screens("filmsScreen")

    data object Search : Screens("search_route")

    data object Profile : Screens("profile_route")

}


data class BottomNavigationItem(
    val lable: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                lable = "Home",
                icon = Icons.Filled.Home,
                route = Screens.PopularMovie.route
            ),
            BottomNavigationItem(
                lable = "Search", icon = Icons.Filled.Search, route = Screens.Search.route
            ),
            BottomNavigationItem(
                lable = "Profile", icon = Icons.Filled.AccountCircle, route = Screens.Profile.route
            )
        )


    }
}