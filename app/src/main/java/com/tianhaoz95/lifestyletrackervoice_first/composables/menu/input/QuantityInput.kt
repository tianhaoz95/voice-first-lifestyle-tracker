package com.tianhaoz95.lifestyletrackervoice_first.composables.menu.input

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel

@Composable
fun QuantityInput(
    model: MenuScreenViewModel = viewModel()
) {
    val quantity: String by model
        .quantity.observeAsState("")
    val quantityAtError: Boolean by model
        .quantityAtError.observeAsState(false)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2.0F)
                .padding(horizontal = Dp(8.0F)),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Quantity: ",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
        }
        OutlinedTextField(
            value = quantity,
            onValueChange = { it ->
                val newValue: Float? = it.toFloatOrNull()
                model.updateQuantity(it)
                model.updateQuantityAtError(newValue == null)
            },
            isError = quantityAtError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(1.0F),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary
            )
        )
    }
}