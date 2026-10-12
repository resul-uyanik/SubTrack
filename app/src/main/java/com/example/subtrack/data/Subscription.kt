package com.example.subtrack.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class Subscription(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val platformAdi: String,
    val fiyat: Double,
    val paraBirimi: String,
    val yenilenmeTarihi: Long,
    val kategori: String,
    val sonKullanimTarihi: Long
)