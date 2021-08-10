package com.tianhaoz95.lifestyletrackervoice_first.types

import android.content.res.Resources
import android.os.Bundle
import android.util.NoSuchPropertyException
import com.tianhaoz95.lifestyletrackervoice_first.R

class HydrationRecord constructor(
    private val type: IntakeItemCategory,
    private val quantity: Int,
    private val unit: IntakeItemUnit,
    private val timestamp: Long
) {
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

    fun toDatabaseEntry(): HashMap<String, Any> {
        return hashMapOf(
            "timestamp" to System.currentTimeMillis(),
            "type" to type.toString(),
            "quantity" to quantity,
            "unit" to unit.toString(),
        )
    }
}
