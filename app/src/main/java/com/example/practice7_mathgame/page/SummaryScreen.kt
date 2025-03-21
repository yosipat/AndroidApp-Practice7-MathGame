package com.example.practice7_mathgame.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen(
    score: Int,
    onClick:()->Unit
) {
    Column(modifier = Modifier.padding(20.dp).fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Total Score : $score")
        Button(onClick=onClick,
            modifier = Modifier.padding(horizontal = 0.dp, vertical = 20.dp)) {
            Text("Restart Game")
        }
    }

}