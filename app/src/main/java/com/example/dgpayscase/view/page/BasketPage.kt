package com.example.dgpayscase.view.page

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dgpayscase.R
import com.example.dgpayscase.ui.theme.DgpaysCaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BasketPage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Basket") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()).fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyColumn {
                    items(30) {
                        val itemCount = remember { mutableIntStateOf(1) }
                        BasketCard(itemCount)
                    }
                }
            }
        },
        bottomBar = {
            BasketBottomBar()
        }
    )
}

@Composable
private fun BasketCard(itemCount: MutableIntState) {
    Card(
        modifier = Modifier
            .padding(all = 5.dp)
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier.size(100.dp, 100.dp),
                    painter = painterResource(id = R.drawable.baseline_image_24),
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(all = 10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "title title title title",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Row(
                        modifier = Modifier
                            .height(40.dp)
                            .border(
                                BorderStroke(
                                    width = 1.dp,
                                    color = Color.Gray
                                ),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            if (itemCount.intValue > 1) itemCount.intValue--
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_remove_24),
                                contentDescription = "",
                                tint = if (itemCount.intValue > 1) Color.Red else Color.Gray
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(30.dp, 30.dp)
                                .background(
                                    colorResource(id = R.color.text_background),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = itemCount.intValue.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Red,
                                textAlign = TextAlign.Center
                            )
                        }
                        IconButton(onClick = {
                            itemCount.intValue++
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_add_24),
                                contentDescription = "",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {

                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier.padding(bottom = 5.dp, end = 10.dp),
                    text = "119.27 TL",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Red
                )
            }

        }
    }
}

@Composable
private fun BasketBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 5.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Toplam")
                Text(text = "1515.58 TL")
            }
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Sepeti Onayla")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasketPagePreview() {
    DgpaysCaseTheme {
        BasketPage(rememberNavController())
    }
}