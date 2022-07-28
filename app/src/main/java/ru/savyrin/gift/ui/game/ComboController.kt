package ru.savyrin.gift.ui.game

class ComboController {

    private val neededCombo = FATALITY_COMBO.map { comboChar ->
        when (comboChar) {
            'L' -> ComboMoves.LEFT
            'R' -> ComboMoves.RIGHT
            'U' -> ComboMoves.UP
            'D' -> ComboMoves.DOWN
            else -> ComboMoves.CLICK
        }
    }
    private val currentCombo = mutableListOf<ComboMoves>()

    fun appendCurrentCombo(comboMove: ComboMoves) {
        val currentComboSize = currentCombo.size
        val comboMoveInNeeded = neededCombo[currentComboSize]
        when  {
            (comboMove != comboMoveInNeeded) -> currentCombo.clear()
            (comboMove == neededCombo[0]) -> currentCombo.add(comboMove)
            else -> currentCombo.add(comboMove)
        }
    }

    fun isComboFinished(): Boolean {
        return currentCombo.size == neededCombo.size
    }

    fun getComboCount(): Int {
        return currentCombo.size
    }

    companion object {
        const val FATALITY_COMBO = "L R U D C R L R U D C L D U C C D U D L C R L D U R D U R"
    }
}