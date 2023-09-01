package com.example.dgpayscase.view.page

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dgpayscase.R
import com.example.dgpayscase.model.Product
import com.example.dgpayscase.ui.theme.DgpaysCaseTheme
import com.example.dgpayscase.view.MainContract
import com.example.dgpayscase.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()
    val productList = remember { mutableStateListOf<Product>() }
    val isDropdownOpen = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        this.launch {
            viewModel.uiState.collect {
                when (it.state) {
                    is MainContract.HomeState.Idle -> {
                        Log.d("bekbek", "Idle")
                    }

                    is MainContract.HomeState.Products -> {
                        Log.d("bekbek", "Products")
                        productList.addAll(it.state.data)
                    }

                    is MainContract.HomeState.Loading -> {
                        Log.d("bekbek", "Loading")
                    }

                    is MainContract.HomeState.Error -> {
                        Log.d("bekbek", "Error ${it.state.error}")
                    }
                }
            }
        }
        viewModel.setEvent(MainContract.Event.FetchProduct)
    }

    LaunchedEffect(key1 = true) {
        this.launch {
            viewModel.effect.collect {
                when (it) {
                    is MainContract.Effect.ShowToast -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = {
                        isDropdownOpen.value = true
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_more_vert_24),
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                    DropdownMenu(
                        expanded = isDropdownOpen.value,
                        onDismissRequest = { isDropdownOpen.value = false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Log out") },
                            onClick = {
                                isDropdownOpen.value = false
                            })
                    }
                }
            )
        },
        content = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (productList.size != 0) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(190.dp),
                    ) {
                        items(productList.size) {
                            val product = productList[it]
                            val itemCount = remember { mutableIntStateOf(0) }
                            HomeCard(navController, itemCount, it)
                        }
                    }
                } else {
                    CircularProgressIndicator()
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_product_page")
                },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
private fun HomeCard(navController: NavController, itemCount: MutableIntState, index: Int) {
    Card(
        modifier = Modifier
            .padding(all = 5.dp)
            .fillMaxWidth()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier.clickable {
                navController.navigate("detail_product_page")
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(150.dp, 150.dp),
                    painter = painterResource(id = R.drawable.baseline_image_24),
                    contentDescription = ""
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "$${index}0.54",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "name $index",
                            fontSize = 15.sp
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(all = 10.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (itemCount.intValue != 0) {
                        CarIconButton(id = R.drawable.baseline_remove_24) {
                            itemCount.intValue--
                        }
                        Box(
                            modifier = Modifier
                                .size(40.dp, 40.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = itemCount.intValue.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                        CarIconButton(id = R.drawable.baseline_add_24) {
                            itemCount.intValue++
                        }
                    } else {
                        CarIconButton(id = R.drawable.baseline_add_shopping_cart_24) {
                            itemCount.intValue++
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CarIconButton(id: Int, onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.size(40.dp, 40.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier,
            painter = painterResource(id),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    DgpaysCaseTheme {
        HomePage(rememberNavController())
    }
}