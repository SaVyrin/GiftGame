package ru.savyrin.gift.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val mutableGameState = MutableLiveData(GameState.NOT_STARTED)
    val gameState: LiveData<GameState> = mutableGameState

    private val game = Game()

    fun startGame() {
        game.start()
    }

    fun finishGame() {
        game.finish()
    }

    fun beatPresent() {
        game.beatPresent()
        mutableGameState.value = game.getGameState()
    }
}