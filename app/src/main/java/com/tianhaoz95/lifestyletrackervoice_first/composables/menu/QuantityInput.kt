package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun QuantityInput(
    viewModel: MenuScreenViewModel,
) {
    val quantity: String by viewModel
        .quantity.observeAsState("")
    val quantityAtError: Boolean by viewModel
        .quantityAtError.observeAsState(false)

    TextField(
        value = quantity,
        onValueChange = { it ->
            val newValue: Float? = it.toFloatOrNull()
            viewModel.updateQuantity(it)
            viewModel.updateQuantityAtError(newValue == null)
        },
        isError = quantityAtError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
    )
}