package com.tianhaoz95.lifestyletrackervoice_first.composables.settings.developer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.models.SettingsViewModel

@Composable
fun IsDeveloper(
    onIsDeveloperChange: (newValue: Boolean) -> Unit,
    model: SettingsViewModel = viewModel()
) {
    val isDeveloper: Boolean by model
        .isDeveloper.observeAsState(false)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Developer",
            modifier = Modifier.padding(Dp(4.0F)),
        )
        Switch(
            checked = isDeveloper,
            onCheckedChange = { onIsDeveloperChange(it) },
            modifier = Modifier.padding(Dp(4.0F))
        )
    }
}