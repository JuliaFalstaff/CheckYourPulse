package com.example.checkyourpulse.domain.model

import java.util.*

data class HealthInfo(
    val date: Date,
    val time: String,
    val pressureLow: Int,
    val pressureHigh: Int,
    val pulse: Int
)
