package com.example.movingapi

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movingapi.presentation.navigation.BottomNavigationItem

import com.example.movingapi.presentation.navigation.NavGraph


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

            val  navController = rememberNavController()
            var showBottomBar by rememberSaveable { mutableStateOf(true) }
            val  navBackStackEntry by navController.currentBackStackEntryAsState()

            showBottomBar = when ( navBackStackEntry?.destination?.route){
                Screens.OnBoarding.route -> false
                else -> true
            }
            val navigationSelectedItem = rememberSaveable{
                mutableIntStateOf(0)
            }
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    if (showBottomBar){
                        NavigationBar {
                            BottomNavigationBar(navigationSelectedItem, navController)
                        }
                    }
                }
            ) {paddingValues ->
                Box (modifier = Modifier.padding(paddingValues)){
                   NavGraph(navController)
                }


            }
        }
    }
}


@Composable
private fun RowScope.BottomNavigationBar(
    navigationSelectedItem: MutableState<Int>,
    navController: NavHostController
) {
    BottomNavigationItem().bottomNavigationItems()
        .forEachIndexed { index, navigationItem ->
            //iterating all items with their respective indexes
            NavigationBarItem(
                selected = index == navigationSelectedItem.value,
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
                    navigationSelectedItem.value = index
                    navController.navigate(navigationItem.route) {
                        popUpToTop(navController)
                        restoreState= true
                        launchSingleTop = true
                    }
                }
            )
        }
}


