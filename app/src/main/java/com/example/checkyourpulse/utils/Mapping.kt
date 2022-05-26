package com.example.checkyourpulse.utils

import android.os.Build
import com.example.checkyourpulse.domain.model.HealthInfo
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val DATE = "date"
const val TIME = "time"
const val PRESSURE_LOW = "pressureLow"
const val PRESSURE_HIGH = "pressureHigh"
const val PULSE = "pulse"

fun convertToDataModel(docData: MutableMap<String, Any>): HealthInfo {
    return HealthInfo(
            date = (docData[DATE] as Timestamp).toDate(),
            time = docData[TIME] as String,
            pressureLow = (docData[PRESSURE_LOW] as Long).toInt(),
            pressureHigh = (docData[PRESSURE_HIGH] as Long).toInt(),
            pulse = (docData[PULSE] as Long).toInt()
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

fun Date.convertToString(): String {
    val formatter = SimpleDateFormat("dd-LL-yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(this)
}

fun Date.convertToDayString(): String {
    val formatter = SimpleDateFormat("dd-LLLL-yyyy", Locale.getDefault())
    return formatter.format(this)
}

fun Date.convertToDayMonthString(): String {
    val formatter = SimpleDateFormat("dd LLLL", Locale.getDefault())
    return formatter.format(this)
}

fun Date.convertToHoursMinutes(): String {
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(this)
}

fun getCurrentDate(): Date {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = SimpleDateFormat("dd-LL-yyyy HH:mm:ss", Locale.getDefault())
        val nowTime = LocalDateTime.now()
        formatter.parse(nowTime.format(DateTimeFormatter.ofPattern("dd-LL-yyyy HH:mm:ss")))

    } else {
        Calendar.getInstance().time
    }
}

fun getCurrentTime(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val nowTime = LocalDateTime.now()
        val current = nowTime.format(DateTimeFormatter.ofPattern("dd-LL-yyyy HH:mm:ss"))
        current.dropLast(17)
    } else {
        Calendar.getInstance().time.convertToString().dropLast(17)
    }
}