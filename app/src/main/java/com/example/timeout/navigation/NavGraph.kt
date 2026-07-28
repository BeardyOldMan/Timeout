package com.example.timeout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timeout.ui.theme.screens.blackjack.BlackJackGameScreen
import com.example.timeout.ui.theme.screens.blackjack.BlackJackLobbyScreen
import com.example.timeout.ui.theme.screens.home.HomeScreen
import com.example.timeout.ui.theme.screens.statistics.StatisticsScreen

@Composable
fun NavGraph(){
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(route=Screen.Home.route){
            HomeScreen(onPlayBlackJack = {navController.navigate(Screen.BlackJackLobby.route)},
                onCheckStatistics = {navController.navigate(Screen.Statistics.route)}
                )
        }
        composable(route=Screen.Statistics.route){
            StatisticsScreen(
                onReturnHome = {navController.popBackStack()}
            )
        }
        composable(route= Screen.BlackJackLobby.route){
            BlackJackLobbyScreen(
                onReturnHome = {navController.popBackStack()},
                onStartGame = {navController.navigate(Screen.BlackJackGame.route)}
            )
        }
        composable(route= Screen.BlackJackGame.route){
            BlackJackGameScreen(
                onReturnLobby = {navController.popBackStack()}
            )
        }
    }
}