package com.tianhaoz95.lifestyletrackervoice_first.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuScreenViewModel @Inject constructor(
    private val userDataService: UserDataService
) : ViewModel() {
    private val _expandType = MutableLiveData(false)
    val expandType: LiveData<Boolean> = _expandType

    private val _expandUnit = MutableLiveData(false)
    val expandUnit: LiveData<Boolean> = _expandUnit

    private val _indexType = MutableLiveData(0)
    val indexType: LiveData<Int> = _indexType

    private val _indexUnit = MutableLiveData(0)
    val indexUnit: LiveData<Int> = _indexUnit

    private val _quantity = MutableLiveData("1.0")
    val quantity: LiveData<String> = _quantity

    private val _quantityAtError = MutableLiveData(false)
    val quantityAtError: LiveData<Boolean> = _quantityAtError

    private val _canSubmit = MutableLiveData(true)
    val canSubmit: LiveData<Boolean> = _canSubmit

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

    fun updateQuantity(newQuantity: String) {
        _quantity.value = newQuantity
    }

    fun updateQuantityAtError(newValue: Boolean) {
        _quantityAtError.value = newValue
        updateCanSubmit()
    }

    fun updateCanSubmit() {
        _canSubmit.value = !_quantityAtError.value!!
    }

    fun getQuantity(): Float {
        return _quantity.value?.toFloatOrNull() ?: 0.0F
    }

    val unitList: List<IntakeItemUnit> get() = userDataService.unitList
    val typeList: List<IntakeItemCategory> get() = userDataService.typeList
}