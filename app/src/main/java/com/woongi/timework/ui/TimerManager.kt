package com.woongi.timework.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerManager {
    private var _timerFlow = MutableStateFlow(0)  // 타이머 상태
    val timerFlow: StateFlow<Int> = _timerFlow

    private var timerJob: Job? = null

    // 타이머 시작
    fun startTimer() {
        if (timerJob == null || timerJob?.isCancelled == true) {
            timerJob = CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    delay(1000)
                    _timerFlow.value += 1
                }
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        resetTimer()
    }

    private fun resetTimer() {
        _timerFlow.value = 0
    }
}