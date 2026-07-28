package com.example.timeout.navigation

sealed class Screen(val route:String){
    data object Home:Screen("home")
    data object Statistics: Screen("statistics")
    data object BlackJackGame: Screen("blackjackgame")
    data object BlackJackLobby: Screen("blackjacklobby")
}