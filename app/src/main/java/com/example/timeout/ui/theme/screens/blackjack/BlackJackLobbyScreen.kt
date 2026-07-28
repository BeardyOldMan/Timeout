package com.example.timeout.ui.theme.screens.blackjack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BlackJackLobbyScreen(
    onStartGame:()->Unit,
    onReturnHome:()->Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Lobby")
        OutlinedButton(onClick = onReturnHome) {
            Text("Home")
        }
        OutlinedButton(onClick = onStartGame) {
            Text("Start Game")
        }
    }
}