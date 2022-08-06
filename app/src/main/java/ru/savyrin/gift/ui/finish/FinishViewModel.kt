package ru.savyrin.gift.ui.finish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FinishViewModel(
    private val greetings: Array<String>
) : ViewModel() {

    private val mutableFinishScreenState = MutableLiveData(FinishScreenState.ANIMATING)
    val finishScreenState: LiveData<FinishScreenState> = mutableFinishScreenState

    private var currentGreetingsIndex = 0

    fun getNextGreeting(): String {
        when {
            noMoreGreetings() -> mutableFinishScreenState.value = FinishScreenState.DONE
            else -> {
                mutableFinishScreenState.value = FinishScreenState.ANIMATING

                val currentGreetings = greetings[currentGreetingsIndex]
                currentGreetingsIndex++

                endAnimating()
                return currentGreetings
            }
        }
        return ""
    }

    private fun endAnimating() {
        viewModelScope.launch {
            delay(1000)
            mutableFinishScreenState.value = FinishScreenState.SHOWING_GREETINGS
        }
    }

    private fun noMoreGreetings(): Boolean {
        return currentGreetingsIndex == greetings.size
    }
}