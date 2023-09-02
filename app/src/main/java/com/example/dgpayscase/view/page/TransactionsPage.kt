package com.example.dgpayscase.view.page

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dgpayscase.R
import com.example.dgpayscase.model.Transaction
import com.example.dgpayscase.ui.theme.DgpaysCaseTheme
import com.example.dgpayscase.util.Constants
import com.example.dgpayscase.view.MainContract
import com.example.dgpayscase.viewmodel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransactionsPage(navController: NavController) {
    val viewModel: TransactionViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(MainContract.Event(MainContract.TransactionEvent.FetchData))
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
            Box(
                modifier = Modifier
                    .fillMaxSize().background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                when (state.value) {
                    is MainContract.TransactionState.Idle,
                    is MainContract.TransactionState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is MainContract.TransactionState.Transactions -> {
                        val list = (state.value as MainContract.TransactionState.Transactions).data
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(190.dp),
                        ) {
                            items(list.count()) {
                                val transaction = list[it]
                                TransactionCard(transaction)
                            }
                        }
                    }
                    is MainContract.TransactionState.Error -> {
                        OutlinedButton(onClick = {
                            viewModel.setEvent(MainContract.Event(MainContract.TransactionEvent.FetchData))
                        }) {
                            Text(text = "Tekrar Dene")
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun TransactionCard(transaction: Transaction) {
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

@Preview(showBackground = true)
@Composable
fun TransactionsPagePreview() {
    DgpaysCaseTheme {
        TransactionsPage(rememberNavController())
    }
}