package com.example.practice7_mathgame.page

import androidx.lifecycle.ViewModel
import com.example.practice7_mathgame.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setNumberOfQuestion(numberOfQuestion: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                numberOfQuestion = numberOfQuestion
            )
        }
    }

    fun setComplete(complete: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                complete = complete
            )
        }
    }

    fun setCurrentQuestion(currentQuestion: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentQuestion = currentQuestion
            )
        }
    }

    fun setScore(score: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                score = score
            )
        }
    }

    fun resetGame(){
_uiState.value=UiState()
    }


}
