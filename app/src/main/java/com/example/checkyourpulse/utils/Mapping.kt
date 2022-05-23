package com.example.checkyourpulse.utils

import com.example.checkyourpulse.domain.model.HealthInfo
import com.google.firebase.Timestamp


fun convertToDataModel(docData: MutableMap<String, Any>): HealthInfo {
    return HealthInfo(
     date = (docData[DATE] as Timestamp).toDate(),
     time = docData[TIME] as String,
     pressureLow = docData[PRESSURE_LOW] as Int,
     pressureHigh = docData[PRESSURE_HIGH] as Int,
     pulse = docData[PULSE] as Int
    )
 }


fun covertToDocuments(healthInfo: HealthInfo): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
    map[DATE] = healthInfo.date
    map[TIME] = healthInfo.time
    map[PRESSURE_LOW] = healthInfo.pressureLow
    map[PRESSURE_HIGH] = healthInfo.pressureHigh
    map[PULSE] = healthInfo.pulse
    return map
}

const val DATE = "date"
const val TIME = "time"
const val PRESSURE_LOW = "pressureLow"
const val PRESSURE_HIGH = "pressureHigh"
const val PULSE = "pulse"
