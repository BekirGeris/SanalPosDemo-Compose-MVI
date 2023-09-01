package com.example.dgpayscase.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dgpayscase.ui.theme.DgpaysCaseTheme
import com.example.dgpayscase.view.page.AddProductPage
import com.example.dgpayscase.view.page.DetailProductPage
import com.example.dgpayscase.view.page.NavigationPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(this.window, false)

        setContent {
            DgpaysCaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPage()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DgpaysCaseTheme {

    }
}


@Composable
fun MainPage() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "navigation_page") {
        composable("navigation_page") {
            NavigationPage(navController)
        }
        composable("add_product_page") {
            AddProductPage(navController)
        }
        composable("detail_product_page") {
            DetailProductPage(navController)
        }
    }
}


