package com.example.dgpayscase.view.page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
    Scaffold(
        content = {
            Box(
                modifier = Modifier.padding(bottom = it.calculateBottomPadding())
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
                                0 -> NavItemIcon(0, R.drawable.baseline_home_24)
                                1 -> NavItemIcon(5, R.drawable.baseline_shopping_basket_24)
                                2 -> NavItemIcon(3, R.drawable.baseline_list_24)
                            }
                        })
                }
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun NavItemIcon(count: Int, id: Int) {
    if (count == 0) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = ""
        )
    } else {
        BadgedBox(
            badge = {
                Box(
                    modifier = Modifier.background(
                        Color.Red,
                        shape = RoundedCornerShape(20.dp)
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(
                            top = 1.dp,
                            bottom = 1.dp,
                            start = 5.dp,
                            end = 5.dp
                        ),
                        text = count.toString(),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }) {
            Icon(
                painter = painterResource(id = id),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationPagePreview() {
    DgpaysCaseTheme {
        NavigationPage(rememberNavController())
    }
}
