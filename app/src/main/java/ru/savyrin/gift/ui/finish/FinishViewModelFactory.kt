package ru.savyrin.gift.ui.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class FinishViewModelFactory(
    private val textToShow: Array<String>
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FinishViewModel::class.java)) {
            return FinishViewModel(textToShow) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}