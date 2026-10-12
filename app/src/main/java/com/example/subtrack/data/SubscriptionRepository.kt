package com.example.subtrack.data

import kotlinx.coroutines.flow.Flow

class SubscriptionRepository(
    private val subscriptionDao: SubscriptionDao,
    private val userPreferences: UserPreferences
) {

    val allSubscriptions: Flow<List<Subscription>> = subscriptionDao.getAllSubscriptions()

    suspend fun insert(subscription: Subscription) {
        subscriptionDao.insertSubscription(subscription)
    }

    suspend fun update(subscription: Subscription) {
        subscriptionDao.updateSubscription(subscription)
    }

    suspend fun delete(subscription: Subscription) {
        subscriptionDao.deleteSubscription(subscription)
    }

    suspend fun getSubscriptionById(id: Int): Subscription? {
        return subscriptionDao.getSubscriptionById(id)
    }

    val defaultCurrencyFlow: Flow<String> = userPreferences.defaultCurrencyFlow
    val budgetLimitFlow: Flow<Double> = userPreferences.budgetLimitFlow

    suspend fun saveDefaultCurrency(currency: String) {
        userPreferences.saveDefaultCurrency(currency)
    }

    suspend fun saveBudgetLimit(limit: Double) {
        userPreferences.saveBudgetLimit(limit)
    }
}