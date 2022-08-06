package ru.savyrin.gift.data.present

class Present(private var presentHealth: Long) {

    fun beat(damage: Long) {
        val newHealth = presentHealth - damage
        presentHealth = when (newHealth > 0) {
            true -> newHealth
            false -> 0
        }
    }

    fun isOpen() = (presentHealth <= 0)

    fun getHealth() = presentHealth
}