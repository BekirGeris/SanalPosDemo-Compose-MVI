package com.example.dgpayscase.view.page

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dgpayscase.R
import com.example.dgpayscase.model.Product
import com.example.dgpayscase.ui.theme.DgpaysCaseTheme
import com.example.dgpayscase.view.MainContract
import com.example.dgpayscase.viewmodel.ProductViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddProductPage(navController: NavController) {
    val context = LocalContext.current
    val viewModel: ProductViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    val productName = remember { mutableStateOf("") }
    val productAmount = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        this.launch {
            viewModel.uiState.collect {
                when (it.value) {
                    is MainContract.ProductState.Idle -> {
                        Log.d("bekbek", "AddProductPage Idle")
                    }
                    is MainContract.ProductState.Loading -> {
                        Log.d("bekbek", "AddProductPage Loading")
                    }
                    is MainContract.ProductState.SaveProduct -> {
                        Log.d("bekbek", "AddProductPage SaveProduct")
                        productName.value = ""
                        productAmount.value = ""
                        Toast.makeText(context, "Ürün eklendi", Toast.LENGTH_LONG).show()
                    }
                    is MainContract.ProductState.Error -> {
                        Log.d("bekbek", "AddProductPage Error: ${it.value.error}")
                        Toast.makeText(context, it.value.error, Toast.LENGTH_LONG).show()
                    }
                    else -> {}
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Product") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .imePadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Image(
                        modifier = Modifier.size(100.dp, 100.dp),
                        painter = painterResource(id = R.drawable.baseline_image_24),
                        contentDescription = ""
                    )
                    TextButton(onClick = {

                    }) {
                        Text(text = "Select Product Image")
                    }
                    TextField(
                        value = productName.value,
                        onValueChange = { productName.value = it },
                        label = { Text(text = "Name") }
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    TextField(
                        value = productAmount.value,
                        onValueChange = { productAmount.value = it },
                        label = { Text(text = "Amount") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    when (state.value) {
                        is MainContract.ProductState.Loading -> {
                            CircularProgressIndicator()
                        }
                        else -> {
                            Button(onClick = {
                                viewModel.setEvent(
                                    MainContract.Event(
                                        MainContract.ProductEvent.SaveProduct(
                                            Product(
                                                0,
                                                Random.nextInt(10_000, 99_999),
                                                productName.value,
                                                productAmount.value,
                                                ""
                                            )
                                        )
                                    )
                                )
                            }) {
                                Text(text = "Save")
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddProductPagePreview() {
    DgpaysCaseTheme {
        AddProductPage(rememberNavController())
    }
}