package com.tianhaoz95.lifestyletrackervoice_first.types.menu

import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory

class MenuItem constructor(
    private val type: IntakeItemCategory
) {
    val getType: IntakeItemCategory get() = type
}