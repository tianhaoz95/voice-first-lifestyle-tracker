package com.tianhaoz95.lifestyletrackervoice_first.matcher

class DescriptionToCategoryConverter {
    fun convert(description: String?): IntakeItemCategory {
        if (description == null) {
            return IntakeItemCategory.Water
        }
        val lowerCaseDescription = description.lowercase()
        if (lowerCaseDescription.contains("coffee")) {
            return IntakeItemCategory.Coffee
        }
        if (lowerCaseDescription.contains("soda")) {
            return IntakeItemCategory.Soda
        }
        return IntakeItemCategory.Water
    }
}