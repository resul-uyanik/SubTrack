package com.example.subtrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.subtrack.data.Subscription
import com.example.subtrack.data.SubscriptionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SubTrackViewModel(private val repository: SubscriptionRepository) : ViewModel() {

    val allSubscriptions: StateFlow<List<Subscription>> = repository.allSubscriptions
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val defaultCurrency: StateFlow<String> = repository.defaultCurrencyFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "TL"
        )

    fun addSubscription(subscription: Subscription) {
        viewModelScope.launch {
            repository.insert(subscription)
        }
    }

    fun deleteSubscription(subscription: Subscription) {
        viewModelScope.launch {
            repository.delete(subscription)
        }
    }

    fun updateDefaultCurrency(currency: String) {
        viewModelScope.launch {
            repository.saveDefaultCurrency(currency)
        }
    }
}

class SubTrackViewModelFactory(private val repository: SubscriptionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubTrackViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SubTrackViewModel(repository) as T
        }
        throw IllegalArgumentException("Bilinmeyen ViewModel sınıfı")
    }
}