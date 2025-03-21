package com.example.practice7_mathgame.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practice7_mathgame.R

@Composable
fun StartScreen(
    onClick: () -> Unit,
    onValueChange: (String) -> Unit,
    inputValue: String,
    modifier: Modifier = Modifier
) {

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.icons8_math_100),
                contentDescription = null,
                modifier = Modifier.width(100.dp).height(100.dp),

                )
            Text("Math Game")




        TextField(
            value = inputValue,
            label = { Text("Number of Question") },
            onValueChange = onValueChange,
            modifier = modifier.padding(horizontal = 0.dp, vertical = 20.dp)
        )
        Button(onClick = onClick) {
            Text("Start Game")
        }  }

}


@Preview
@Composable
fun StartScreenPreview() {

    //StartScreen()

}