package com.woongi.timework

import androidx.lifecycle.ViewModel
import com.woongi.timework.ui.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(

): ViewModel() {

    private val _firstItem = MutableStateFlow(
        List(50) { index ->
            Item(
                id = index,
                text = ('a'..'z').random().toString()
            )
        }
    )
    val firstItem = _firstItem.asStateFlow()

    private val _secondItem = MutableStateFlow<List<Item>>(emptyList())
    val secondItem = _secondItem.asStateFlow()


    fun throwAway(item: Item) {
        item.startTimer()
        _firstItem.value = _firstItem.value - item
        _secondItem.value = (_secondItem.value + item).sortedBy { it.id }
    }

    fun pickUp(item: Item) {
        item.stopTimer()
        _firstItem.value = (_firstItem.value + item).sortedBy { it.id }
        _secondItem.value = _secondItem.value - item
    }

    fun remove(item: Item) {
        item.stopTimer()
        _secondItem.value  = _secondItem.value - item
    }
}
