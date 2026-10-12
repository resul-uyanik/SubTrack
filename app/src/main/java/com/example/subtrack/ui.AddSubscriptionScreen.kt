package com.example.subtrack.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.subtrack.data.Subscription
import com.example.subtrack.viewmodel.SubTrackViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubscriptionScreen(
    viewModel: SubTrackViewModel,
    onNavigateBack: () -> Unit
) {
    var platformAdi by remember { mutableStateOf("") }
    var fiyat by remember { mutableStateOf("") }
    var paraBirimi by remember { mutableStateOf("TL") }
    var kategori by remember { mutableStateOf("Eğlence") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yeni Abonelik Ekle") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = platformAdi,
                onValueChange = { platformAdi = it },
                label = { Text("Platform Adı (Örn: Netflix)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fiyat,
                onValueChange = { fiyat = it },
                label = { Text("Fiyat (Örn: 149.99)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = paraBirimi,
                onValueChange = { paraBirimi = it },
                label = { Text("Para Birimi (TL, USD, EUR)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = kategori,
                onValueChange = { kategori = it },
                label = { Text("Kategori (Eğlence, Eğitim, Araçlar)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (platformAdi.isNotBlank() && fiyat.isNotBlank()) {
                        val newSubscription = Subscription(
                            platformAdi = platformAdi,
                            fiyat = fiyat.toDoubleOrNull() ?: 0.0,
                            paraBirimi = paraBirimi,
                            yenilenmeTarihi = System.currentTimeMillis(),
                            kategori = kategori,
                            sonKullanimTarihi = System.currentTimeMillis()
                        )
                        viewModel.addSubscription(newSubscription)

                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kaydet")
            }
        }
    }
}