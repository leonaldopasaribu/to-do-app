package com.example.to_do_app.presentation.viewmodel

import androidx.navigation.NavHostController

class OnboardingViewModel (){
    fun navigateToHome(navController: NavHostController) {
        navController.navigate("home") {
            popUpTo("onboarding") { inclusive = true }
        }
    }
}