package com.example.to_do_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.to_do_app.presentation.screen.AddTaskScreen
import com.example.to_do_app.presentation.screen.DetailTaskScreen
import com.example.to_do_app.presentation.screen.HomeScreen
import com.example.to_do_app.presentation.screen.OnboardingScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") { OnboardingScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("addTask") { AddTaskScreen(navController) }
        composable("detailTask/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toInt() ?: 0
            DetailTaskScreen(navController, taskId)
        }
    }
}