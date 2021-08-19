package com.tianhaoz95.lifestyletrackervoice_first.types

import android.content.res.Resources
import android.os.Bundle
import android.util.NoSuchPropertyException
import com.tianhaoz95.lifestyletrackervoice_first.R
import java.util.*
import kotlin.collections.HashMap

class HydrationRecord constructor(
    val type: IntakeItemCategory,
    val quantity: Int,
    val unit: IntakeItemUnit,
    val timestamp: Long
) {
    private val _unitToLiterConversionDict: HashMap<IntakeItemUnit, Float> =
        hashMapOf(
            IntakeItemUnit.Cup to 0.2F,
            IntakeItemUnit.Bottle to 0.5F,
            IntakeItemUnit.Can to 0.3F,
            IntakeItemUnit.Milliliter to 0.01F,
            IntakeItemUnit.Liter to 1.0F,
        )

    companion object Factory {
        fun fromExtras(bundle: Bundle?): HydrationRecord {
            if (bundle == null) {
                throw NoSuchPropertyException(
                    Resources
                        .getSystem()
                        .getString(
                            R.string.no_record_information_found_in_intent))
            }
            return HydrationRecord(
                nameToCategory(
                    bundle.getString("name").orEmpty()),
                1,
                IntakeItemUnit.Cup,
                System.currentTimeMillis(),
            )
        }

        private fun nameToCategory(description: String?): IntakeItemCategory {
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

    fun toLiter(): Float {
        return _unitToLiterConversionDict[unit]?.times(quantity) ?: 0.2F
    }

    fun toDatabaseEntry(): HashMap<String, Any> {
        return hashMapOf(
            "timestamp" to System.currentTimeMillis(),
            "type" to type.toString(),
            "quantity" to quantity,
            "unit" to unit.toString(),
        )
    }
}
