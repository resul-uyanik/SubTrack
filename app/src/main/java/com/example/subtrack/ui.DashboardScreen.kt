package com.example.subtrack.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.subtrack.data.Subscription
import com.example.subtrack.viewmodel.SubTrackViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: SubTrackViewModel,
    onNavigateToAdd: () -> Unit
) {
    val subscriptions by viewModel.allSubscriptions.collectAsState()
    val defaultCurrency by viewModel.defaultCurrency.collectAsState()

    val totalExpense = subscriptions.sumOf { it.fiyat }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SubTrack", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, contentDescription = "Abonelik Ekle")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Aylık Toplam Harcama", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$totalExpense $defaultCurrency",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Aboneliklerim", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            if (subscriptions.isEmpty()) {
                Text(text = "Henüz bir abonelik eklemedin.", modifier = Modifier.padding(top = 16.dp))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(subscriptions) { sub ->
                        SubscriptionItem(sub)
                    }
                }
            }
        }
    }
}

@Composable
fun SubscriptionItem(subscription: Subscription) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = subscription.platformAdi, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = subscription.kategori, style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                text = "${subscription.fiyat} ${subscription.paraBirimi}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}