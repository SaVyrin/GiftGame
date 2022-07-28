package ru.savyrin.gift.ui.game

class Present(presentHealth: Long) {
    private var health: Long = presentHealth
    private val color = ""

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

    fun getHealth(): Long {
        return health
    }
}