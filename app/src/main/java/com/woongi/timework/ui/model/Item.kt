package com.woongi.timework.ui.model

import com.woongi.timework.ui.TimerManager


data class Item(
    val id: Int,
    val text: String,
){
    val timerManager: TimerManager = TimerManager()

    fun startTimer() {
        timerManager.startTimer()
    }

    fun stopTimer() {
        timerManager.stopTimer()
    }
}
