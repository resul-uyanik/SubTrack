package com.example.subtrack.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class UserPreferences(private val context: Context) {

    companion object {
        val DEFAULT_CURRENCY = stringPreferencesKey("default_currency")
        val BUDGET_LIMIT = doublePreferencesKey("budget_limit")
    }

    val defaultCurrencyFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[DEFAULT_CURRENCY] ?: "TL"
    }

    val budgetLimitFlow: Flow<Double> = context.dataStore.data.map { preferences ->
        preferences[BUDGET_LIMIT] ?: 0.0
    }

    suspend fun saveDefaultCurrency(currency: String) {
        context.dataStore.edit { preferences ->
            preferences[DEFAULT_CURRENCY] = currency
        }
    }

    suspend fun saveBudgetLimit(limit: Double) {
        context.dataStore.edit { preferences ->
            preferences[BUDGET_LIMIT] = limit
        }
    }
}