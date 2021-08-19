package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuScreenViewModel : ViewModel() {
    private val _expandType = MutableLiveData(false)
    val expandType: LiveData<Boolean> = _expandType

    private val _expandUnit = MutableLiveData(false)
    val expandUnit: LiveData<Boolean> = _expandUnit

    private val _indexType = MutableLiveData(0)
    val indexType: LiveData<Int> = _indexType

    private val _indexUnit = MutableLiveData(0)
    val indexUnit: LiveData<Int> = _indexUnit

    private val _quantity = MutableLiveData(0)
    val quantity: LiveData<Int> = _quantity

    fun updateExpandType(newValue: Boolean) {
        _expandType.value = newValue
    }

    fun updateExpandUnit(newValue: Boolean) {
        _expandUnit.value = newValue
    }

    fun flipExpandType() {
        _expandType.value = !_expandType.value!!
    }

    fun flipExpandUnit() {
        _expandUnit.value = !_expandUnit.value!!
    }

    fun updateTypeIndex(newIndex: Int) {
        _indexType.value = newIndex
    }

    fun updateUnitIndex(newIndex: Int) {
        _indexUnit.value = newIndex
    }

    fun getCurrentTypeIndex(): Int {
        return _indexType.value ?: 0
    }

    fun updateQuantity(newQuantity: Int) {
        _quantity.value = newQuantity
    }
}