package com.tianhaoz95.lifestyletrackervoice_first.types

import com.tianhaoz95.lifestyletrackervoice_first.types.report.CurrentDaySummary

class HydrationReport constructor(private val records: List<HydrationRecord>) {
    fun toCurrentDaySummary(): CurrentDaySummary {
        var summary = CurrentDaySummary(totalCaffeineIntake = 0, totalWaterIntake = 0)
        return summary
    }
}