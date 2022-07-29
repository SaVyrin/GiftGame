package ru.savyrin.gift.ui.finish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FinishViewModel(
    private val greetings: Array<String>
) : ViewModel() {

    private val mutableFinishScreenState = MutableLiveData(FinishScreenState.SHOWING_GREETINGS)
    val finishScreenState: LiveData<FinishScreenState> = mutableFinishScreenState

    private var currentGreetingsIndex = 0

    fun getNextGreeting(): String {
        // TODO fix not all greetings showing
        when {
            noMoreGreetings() -> mutableFinishScreenState.value = FinishScreenState.DONE
            else -> {
                val currentGreetings = greetings[currentGreetingsIndex]
                currentGreetingsIndex++
                return currentGreetings
            }
        }
        return ""
    }

    private fun noMoreGreetings(): Boolean {
        return currentGreetingsIndex == (greetings.size - 1)
    }
}