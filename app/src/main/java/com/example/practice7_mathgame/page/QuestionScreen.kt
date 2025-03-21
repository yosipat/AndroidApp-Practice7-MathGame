package com.example.practice7_mathgame.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.practice7_mathgame.data.UiState

@Composable
fun QuestionScreen(
    question: Pair<String, String>,
    onClick: () -> Unit,
    onCancelClick: () -> Unit,
    inputValue: String,
    onValueChange: (String) -> Unit,
    UiState: UiState,
    modifier: Modifier = Modifier
) {
    Column {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Question#" + UiState.currentQuestion.toString())
                Text("Score : " + UiState.score.toString())
            }
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 0.dp,
                    vertical = 20.dp
                )
            ) { Text(question.first) }

            TextField(
                value = inputValue,
                onValueChange = onValueChange,
                modifier = modifier
            )
            Button(
                onClick = onClick,
                modifier = Modifier.padding(
                    horizontal = 0.dp,
                    vertical = 20.dp
                )
            ) { Text("Submit") }


        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text("Question "+UiState.currentQuestion.toString() + "/" + UiState.numberOfQuestion.toString())
                Button(
                    onClick = onCancelClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red)


                ) {
                    Text("Cancel")
                }
            }

        }
    }

}