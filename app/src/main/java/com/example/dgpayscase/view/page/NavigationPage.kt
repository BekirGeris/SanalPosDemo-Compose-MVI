package com.example.dgpayscase.view.page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dgpayscase.R
import com.example.dgpayscase.ui.theme.DgpaysCaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateValueProperty")
@Composable
fun NavigationPage(navController: NavController) {
    val items = listOf("Home", "Basket", "Transactions")
    val selectionItem = remember { mutableIntStateOf(0) }
    val isDropdownOpen = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = items[selectionItem.value]) },
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
            ) {
                when (selectionItem.value) {
                    0 -> HomePage(navController)
                    1 -> BasketPage(navController)
                    2 -> TransactionsPage(navController)
                }
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = Color.White
            ) {
                items.forEachIndexed { index, string ->
                    NavigationBarItem(
                        selected = selectionItem.value == index,
                        onClick = { selectionItem.value = index },
                        label = { Text(text = string) },
                        icon = {
                            when (index) {
                                0 -> Icon(
                                    painter = painterResource(id = R.drawable.baseline_home_24),
                                    contentDescription = ""
                                )

                                1 -> Icon(
                                    painter = painterResource(id = R.drawable.baseline_shopping_basket_24),
                                    contentDescription = ""
                                )

                                2 -> Icon(
                                    painter = painterResource(id = R.drawable.baseline_list_24),
                                    contentDescription = ""
                                )
                            }
                        })
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NavigationPagePreview() {
    DgpaysCaseTheme {
        NavigationPage(rememberNavController())
    }
}
