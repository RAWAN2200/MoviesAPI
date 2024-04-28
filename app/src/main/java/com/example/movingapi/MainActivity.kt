package com.example.movingapi

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movingapi.presentation.navigation.BottomNavigationItem
import com.example.movingapi.presentation.navigation.MovieNavGraph


import com.example.movingapi.presentation.navigation.Screens
import com.example.movingapi.presentation.navigation.popUpToTop


//import com.example.movingapi.presentation.Screen

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//                OnBoardingScreen()
//            Nav(popularViewModel = viewModel )
//            NavGraph()

            val navController = rememberNavController()

            var showBottomBar by rememberSaveable { mutableStateOf(true) }
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            showBottomBar = when (navBackStackEntry?.destination?.route) {
                Screens.OnBoarding.route -> false // on this screen bottom bar should be hidden
                else -> true // in all other cases show bottom bar
            }
            val navigationSelectedItem = rememberSaveable {
                mutableIntStateOf(0)
            }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    if (showBottomBar) {
                        NavigationBar {
                            BottomNavigationBar(navigationSelectedItem, navController)
                        }
                    }
                }
            ) { paddingValues ->
                //We need to setup our NavHost in here
                Box(modifier = Modifier.padding(paddingValues)) {
                    MovieNavGraph(navController)
                }
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavigationBar(
    navigationSelectedItem: MutableIntState,
    navController: NavHostController
) {
    BottomNavigationItem().bottomNavigationItems()
        .forEachIndexed { index, navigationItem ->
            //iterating all items with their respective indexes
            NavigationBarItem(
                selected = index == navigationSelectedItem.intValue,
                label = {
                    Text(navigationItem.lable)
                },
                icon = {
                    Icon(
                        navigationItem.icon,
                        contentDescription = navigationItem.lable
                    )
                },
                onClick = {
                    navigationSelectedItem.intValue = index
                    navController.navigate(navigationItem.route) {
                        popUpToTop(navController)
                    }
                }
            )
        }
}
