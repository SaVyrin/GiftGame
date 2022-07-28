package ru.savyrin.gift.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val mutableGameState = MutableLiveData(GameState.NOT_FINISHED)
    val gameState: LiveData<GameState> = mutableGameState
}