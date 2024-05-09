package com.example.movingapi.presentation.Screen.onBoarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movingapi.R

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movingapi.presentation.navigation.Screens
import com.example.movingapi.presentation.navigation.popUpToTop
import com.example.movingapi.ui.theme.DarkGreenBlue
import com.example.movingapi.ui.theme.LightYellow
import com.example.movingapi.presentation.Screen.onBoarding.OnBoardingViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onBoardingViewModel: OnBoardingViewModel, navController: NavHostController) {
    val onBoardingCompleted by onBoardingViewModel.onBoardingCompleted.collectAsState()

    if (onBoardingCompleted) {
        navController.navigate(Screens.PopularMovie.route){
            popUpToTop(navController)
        }
    } else {
        val pagerState = rememberPagerState(
            0,
            pageCount = {
                3
            })
        HorizontalPager(state = pagerState) { page ->
            Column {
                Row(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(DarkGreenBlue)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    (0..2).forEach { index ->
                        val color =
                            if (pagerState.currentPage == index) LightYellow else Color.White
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(color, CircleShape)
                                .size(10.dp)
                        )
                    }
                }
                when (page) {
                    0 -> {
                        onBoardingScreen1()
                    }

                    1 -> {
                        onBoardingScreen2()
                    }

                    2 -> {
                        onBoardingScreen3(navController) {
//                          OnBoardingViewModel.saveOnBoardingState(true)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun onBoardingScreen1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreenBlue),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
//                .rotate(-90f)
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.brulli),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.movienight),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun onBoardingScreen2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreenBlue),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
//                .rotate(-90f)
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.brulli),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.homecinima),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun onBoardingScreen3(navController: NavHostController, onFinishClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreenBlue),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
//                .rotate(-90f)
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.brulli),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.horrmovies),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Row(modifier = Modifier
                    .padding(80.dp)
                    .fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = true
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(Screens.PopularMovie.route){
                                    popUpToTop(navController)
                                }
                                onFinishClick.invoke()
                            },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White
                            ),
                            modifier = Modifier .padding(90.dp),
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Text(text = "Go to the movies", modifier = Modifier.padding(4.dp))
                        }
                    }
                }
            }
        }
    }
}





















//@Composable
//fun onBoardingScreen1(navController: NavController){
//Column( modifier = Modifier
//    .fillMaxSize()
//    .background(Color(0xFF647D87)),) {
////    Modifier.background(Color.LightGray)
//    Image(painter = painterResource(id = R.drawable.movienight), contentDescription = "", Modifier.size(450.dp))
//    Text(text = "Welcome to CineSpectra !", fontSize = 29.sp,fontWeight = FontWeight.Bold  , modifier = Modifier.padding(start = 40.dp))
//    Text(text = "Explore the latest movies, reserve the perfect seats, and ", modifier = Modifier.padding(start = 40.dp), Color.White)
//    Text(text = "experience the cinema in a whole new way.", modifier = Modifier.padding(start = 70.dp), Color.White)
//    Image(painter = painterResource(id = R.drawable.brulli), contentDescription = "" , colorFilter = ColorFilter.tint(Color(0xFFF6D776)) ,
//        modifier = Modifier.size(420.dp).padding(top = 100.dp)
//     )
//
//}
//    Row( modifier = Modifier
//        .fillMaxSize().padding(start = 120.dp, top = 740.dp)){
//        Button(onClick = {navController.navigate(route = "B") } , colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF647D87)) ) {
//            Text(text = "Lets's get started" )
//
//        }
//    }
//}
//
//@Composable
//fun onBoardingScreen2(navController: NavController){
//    Column( modifier = Modifier
//        .fillMaxSize()
//        .background(Color(0xFF647D87)),) {
////    Modifier.background(Color.LightGray)
//        Image(painter = painterResource(id = R.drawable.homecinima), contentDescription = "", Modifier.size(450.dp))
//        Text(text = "Quick and Easy Booking", fontSize = 29.sp,fontWeight = FontWeight.Bold   , modifier = Modifier.padding(start = 50.dp))
//        Text(text = "Reserve your favorite seat in just a few steps. No!" ,modifier = Modifier.padding(start = 50.dp), Color.White)
//        Text(text = "  waiting, no hassle;", modifier = Modifier.padding(start = 150.dp), Color.White)
//        Image(painter = painterResource(id = R.drawable.brulli), contentDescription = "" , colorFilter = ColorFilter.tint(Color(0xFFF6D776)) ,
//            modifier = Modifier.size(420.dp).padding(top = 100.dp)
//        )
//
//    }
//    Row( modifier = Modifier
//        .fillMaxSize().padding(start = 120.dp, top = 740.dp)){
//        Button(onClick = {navController.navigate(route = "C") } , colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF647D87)) ) {
//            Text(text = "Lets's get started" )
//
//        }
//    }
//}
//
//
//@Composable
//fun onBoardingScreen3(navController: NavController){
//
//    Column( modifier = Modifier
//        .fillMaxWidth()
//        .background(Color(0xFF647D87)),
//    ) {
////    Modifier.background(Color.LightGray)
//
//        Image(painter = painterResource(id = R.drawable.horrmovies), contentDescription = "", Modifier.size(450.dp))
////        Image(painter = painterResource(id = R.drawable.scrol3), contentDescription = "", Modifier.size(50.dp).padding(top = 1.dp) )
//        Text(text = "Tailored Just for You", fontSize = 29.sp,fontWeight = FontWeight.Bold  , modifier = Modifier.padding(start = 50.dp))
//        Text(text = " Personalize your experience! With movie ", modifier = Modifier.padding(start = 60.dp), Color.White)
//        Text(text = " recommendations and exclusive offers, enjoy the ", modifier = Modifier.padding(start = 40.dp), Color.White)
//        Text(text = " cinema your way.", modifier = Modifier.padding(start = 150.dp), Color.White)
//
////        Button(onClick = { navController.navigate(route = "filmsScreen(viewModels = popularViewModel)") }) {
////            Text(text = "Lets's get started")
////        }
//        Image(painter = painterResource(id = R.drawable.brulli), contentDescription = "" , colorFilter = ColorFilter.tint(Color(0xFFF6D776)) ,
//            modifier = Modifier
//                .size(450.dp)
//                .padding(top = 100.dp)
//        )
//
//    }
//    Row( modifier = Modifier
//        .fillMaxSize()
//        .padding(start = 120.dp, top = 740.dp)){
//        Button(onClick = { navController.navigate(route = "filmsScreen")} , colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF647D87)) ) {
//            Text(text = "Lets's get started" )
//
//        }
//    }
//}
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun OnBoardingScreen(navController: NavHostController) {
//    val pagerState = rememberPagerState { 3 }
//    val navController = rememberNavController()
//    HorizontalPager(
//        state = pagerState,
//        modifier = Modifier.fillMaxSize(),
//        pageSize = PageSize.Fill,
//
//
//        ) {
//        when (pagerState.currentPage) {
//            0 -> onBoardingScreen1(navController)
//            1 -> onBoardingScreen2(navController)
//            2 -> onBoardingScreen3(navController)
//        }
//
//    }
//    Row(
//        modifier = Modifier
//            .wrapContentHeight()
//            .fillMaxWidth()
//            .padding(bottom = 8.dp),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        repeat(pagerState.pageCount) { iteration ->
//            val color =
//                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primaryContainer
//            Box(
//                modifier = Modifier
//                    .padding(2.dp)
//                    .clip(CircleShape)
//                    .background(color)
//                    .size(16.dp)
//            )
//        }
//    }
//}
