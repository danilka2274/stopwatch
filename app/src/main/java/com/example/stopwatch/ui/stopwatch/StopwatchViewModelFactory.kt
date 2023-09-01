package com.example.stopwatch.ui.stopwatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch.model.Stopwatch

class StopwatchViewModelFactory(
    private val stopwatchBuilder: () -> Stopwatch
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Stopwatch::class.java).newInstance(stopwatchBuilder())
    }
}