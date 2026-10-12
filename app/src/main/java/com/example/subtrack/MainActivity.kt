package com.example.subtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.subtrack.data.AppDatabase
import com.example.subtrack.data.SubscriptionRepository
import com.example.subtrack.data.UserPreferences
import com.example.subtrack.ui.AddSubscriptionScreen
import com.example.subtrack.ui.DashboardScreen
import com.example.subtrack.ui.theme.SubTrackTheme
import com.example.subtrack.viewmodel.SubTrackViewModel
import com.example.subtrack.viewmodel.SubTrackViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val userPreferences = UserPreferences(this)
        val repository = SubscriptionRepository(database.subscriptionDao(), userPreferences)

        val viewModel: SubTrackViewModel by viewModels {
            SubTrackViewModelFactory(repository)
        }

        setContent {
            SubTrackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "dashboard") {

                        composable("dashboard") {
                            DashboardScreen(
                                viewModel = viewModel,
                                onNavigateToAdd = {
                                    navController.navigate("add_subscription")
                                }
                            )
                        }

                        composable("add_subscription") {
                            AddSubscriptionScreen(
                                viewModel = viewModel,
                                onNavigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}