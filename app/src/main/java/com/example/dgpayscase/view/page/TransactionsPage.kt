package com.example.dgpayscase.view.page

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
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
import com.example.dgpayscase.model.Transaction
import com.example.dgpayscase.ui.theme.DgpaysCaseTheme
import com.example.dgpayscase.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransactionsPage(navController: NavController) {
    val transactions = remember { mutableStateListOf<Transaction>() }

    LaunchedEffect(key1 = true) {
        val list = listOf(
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(2, 2, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(2, 2, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(2, 2, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(2, 2, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(2, 2, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(2, 2, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(1, 1, "1564163134", "2023/07/07 15:54", "155.45"),
            Transaction(2, 2, "1564163134", "2023/07/07 15:54", "155.45")
        )
        transactions.addAll(list)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Transactions") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_filter_list_24),
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = {
            LazyColumn(
                contentPadding = it
            ) {
                items(transactions.count()) {
                    val transaction = transactions[it]
                    Card(
                        modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth()
                            .height(100.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {

                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(all = 5.dp)
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = if (transaction.txnType == Constants.SALE) "Satış" else "İptal",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = transaction.txnId,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = transaction.txnDate,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Text(
                                    text = "$ ${transaction.totalAmount}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    color = if (transaction.txnType == Constants.SALE) colorResource(
                                        id = R.color.derk_green
                                    ) else Color.Red
                                )
                                if (transaction.txnType == Constants.SALE) {
                                    IconButton(onClick = {

                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                                            contentDescription = "",
                                            tint = Color.Black
                                        )
                                    }
                                }
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
fun TransactionsPagePreview() {
    DgpaysCaseTheme {
        TransactionsPage(rememberNavController())
    }
}