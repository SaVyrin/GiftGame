package ru.savyrin.gift.ui.game

import kotlin.math.pow

class Game {
    private lateinit var present: Present

    private var currentGameLevel = INITIAL_GAME_LEVEL
    private var powerMultiplayer = INITIAL_POWER_MULTIPLAYER

    private val comboController = ComboController()

    private var gameState = GameState.NOT_FINISHED

    fun start() {
        setNewPresent()
    }

    private fun finish() {
        gameState = GameState.FINISHED
    }

    fun beatPresent() {
        val damage = CLICK_POWER * powerMultiplayer
        present.beat(damage)
        if (present.isOpen()) {
            setNewPresent()
        }
        appendClickCombo()
    }

    private fun appendClickCombo() {
        comboController.appendCurrentCombo(ComboMoves.CLICK)
        checkComboFinished()
        powerMultiplayer = comboController.getComboCount()
    }

    fun appendLeftCombo() {
        comboController.appendCurrentCombo(ComboMoves.LEFT)
        checkComboFinished()
        powerMultiplayer = comboController.getComboCount()
    }

    fun appendRightCombo() {
        comboController.appendCurrentCombo(ComboMoves.RIGHT)
        checkComboFinished()
        powerMultiplayer = comboController.getComboCount()
    }

    fun appendDownCombo() {
        comboController.appendCurrentCombo(ComboMoves.DOWN)
        checkComboFinished()
        powerMultiplayer = comboController.getComboCount()
    }

    fun appendUpCombo() {
        comboController.appendCurrentCombo(ComboMoves.UP)
        checkComboFinished()
        powerMultiplayer = comboController.getComboCount()
    }

    private fun checkComboFinished() {
        if (comboController.isComboFinished()) {
            finish()
        }
    }

    fun getGameState(): GameState {
        return gameState
    }

    fun getPresentHealth(): Long {
        return present.getHealth()
    }

    private fun setNewPresent() {
        goToNextLevel()
        val newPresentHealth = PRESENT_HEALTH_MULTIPLAYER.pow(currentGameLevel).toLong()
        present = Present(newPresentHealth)
    }

    private fun goToNextLevel() {
        currentGameLevel++
    }

    companion object {
        const val INITIAL_GAME_LEVEL = 0
        const val CLICK_POWER = 10L
        const val INITIAL_POWER_MULTIPLAYER = 1
        const val PRESENT_HEALTH_MULTIPLAYER = 100.0
    }
}