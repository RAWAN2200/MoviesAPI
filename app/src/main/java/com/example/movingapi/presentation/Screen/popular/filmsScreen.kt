package com.example.movingapi.presentation.Screen.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movingapi.model.BackdropSize
import com.example.movingapi.model.UIState
import com.example.movingapi.Constant
import com.example.movingapi.Constant.MOVIE_IMAGE_BASE_URL
import com.example.movingapi.R
import com.example.movingapi.model.Results
import com.example.movingapi.model.SearchResponse
import com.example.movingapi.presentation.navigation.Screens
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun filmsScreen(navController: NavHostController, popularMoviesState: MutableStateFlow<PagingData<Results>>
) {
    val moviePagingItems = popularMoviesState.collectAsLazyPagingItems()
    Box{
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center
        ) {
            items(moviePagingItems.itemCount) { index ->
                if(moviePagingItems[index]?.adult==false) {
                    AsyncImage(
                        model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${moviePagingItems[index]?.posterPath}",
                        contentDescription = "",
                        modifier = Modifier
                            .padding(2.dp)
                            .clickable {
                                navController.navigate(Screens.MovieDetail.route + "/${moviePagingItems[index]?.id}")
                            },
                        contentScale = ContentScale.FillWidth,
                        error = painterResource(R.drawable.ic_launcher_background),
                        placeholder = painterResource(R.drawable.ic_launcher_background)
                    )
                }
            }
        }
        moviePagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {

                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator()
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = moviePagingItems.loadState.refresh as LoadState.Error
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = error.error.localizedMessage.orEmpty(),
                            modifier = Modifier,
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        CircularProgressIndicator()
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = moviePagingItems.loadState.append as LoadState.Error
                    Text(
                        text = error.error.localizedMessage.orEmpty(),
                        modifier = Modifier,
                    )
                }
            }
        }
    }

}
















//
//@Composable
//fun filmsScreen(navController: NavHostController, popularMoviesState: MutableState<UIState<SearchResponse>>
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.primaryContainer)
//    )
//    {
//
//        when (val result = popularMoviesState.value) {
//            is UIState.Success -> {
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.secondary)
//                ) {
//                    items(result.data?.results.orEmpty()) {
//                        Text(
//                            text = it.title.orEmpty(),
//                            modifier = Modifier.padding(12.dp)
//                        )
//                        AsyncImage(
//                            model = ImageRequest.Builder(LocalContext.current)
//                                .data("${Constant.MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${it.posterPath}")
//                                .build(), contentDescription = ""
//                        )
//                    }
//                }
//            }
//
//            is UIState.Empty -> {}
//            is UIState.Error -> {}
//            is UIState.Loading -> {}
//        }
//    }
//}