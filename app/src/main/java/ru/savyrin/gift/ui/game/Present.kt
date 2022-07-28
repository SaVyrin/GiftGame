package ru.savyrin.gift.ui.game

class Present(presentHealth: Long) {
    var health: Long = presentHealth
    val color = ""

    fun beat(damage: Long) {
        val newHealth = health - damage
        health = when (newHealth > 0) {
            true -> newHealth
            false -> 0
        }
    }

    fun isOpen(): Boolean {
        return health <= 0
    }
}