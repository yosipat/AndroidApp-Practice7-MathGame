package com.example.practice7_mathgame

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.practice7_mathgame.data.DataSource
import com.example.practice7_mathgame.data.UiState
import com.example.practice7_mathgame.page.QuestionScreen
import com.example.practice7_mathgame.page.StartScreen
import com.example.practice7_mathgame.page.SummaryScreen
import com.example.practice7_mathgame.page.ViewModel

enum class MathScreen(@StringRes val title: Int) {
    Start(title = R.string.appname),
    Question(title = R.string.question),
    Result(title = R.string.result),
    Summary(title = R.string.summary)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MathAppBar(
    currentScreen: MathScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = stringResource(R.string.back_button)
//                    )
//                }
//            }
//        }
    )
}

@Composable
fun MathApp(
    viewModel: ViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MathScreen.valueOf(
        backStackEntry?.destination?.route ?: MathScreen.Start.name
    )



    Scaffold(
        topBar = {
            MathAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        var inputNumberOfQuestion by remember { mutableStateOf("") }
        var inputAnswer by remember { mutableStateOf("") }
        var currentQuestion = uiState.currentQuestion
        var numberOfQuestion = uiState.numberOfQuestion
        var complete = uiState.complete
        var score = uiState.score


        NavHost(
            navController = navController,
            startDestination = MathScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MathScreen.Start.name) {
                StartScreen(
                    onValueChange = { inputNumberOfQuestion = it },
                    inputValue = inputNumberOfQuestion,
                    onClick = {
                        if (inputNumberOfQuestion.toIntOrNull() != null) {
                            viewModel.setNumberOfQuestion(inputNumberOfQuestion.toInt())
                            navController.navigate(MathScreen.Question.name)
                        }
                    },
                    modifier = Modifier
                )
            }


            composable(route = MathScreen.Question.name) {
                QuestionScreen(
                    question = (DataSource.question[currentQuestion - 1]),
                    UiState = uiState,
                    inputValue = inputAnswer,
                    onValueChange = { inputAnswer = it },
                    onClick = {
                        if (inputAnswer == DataSource.question[currentQuestion - 1].second) {
                            viewModel.setScore(score + 1)
                        }
                        inputAnswer = ""
                        complete = currentQuestion
                        currentQuestion += 1
                        viewModel.setCurrentQuestion(currentQuestion)
                        viewModel.setComplete(complete)
                        if (complete == numberOfQuestion) {
                            navController.navigate(MathScreen.Summary.name)
                        }
                    },
                    onCancelClick = {inputAnswer = ""
                        inputNumberOfQuestion = ""
                        CancelButton(viewModel,navController)},
                    modifier = Modifier
                )
            }



            composable(route = MathScreen.Summary.name) {
                SummaryScreen(
                    score = score,
                    onClick = {
                        inputAnswer = ""
                        inputNumberOfQuestion = ""
                        CancelButton(viewModel,navController)
                    }
                )

            }


        }
    }
}

private fun CancelButton(viewModel: ViewModel,navController: NavHostController){
    viewModel.resetGame()
    navController.popBackStack(MathScreen.Start.name, inclusive = false)
}





