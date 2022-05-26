package com.example.checkyourpulse.domain.model

import java.util.*

data class HealthInfo(
    val date: Date,
    val time: String = "15:24",
    val pressureLow: Int = 120,
    val pressureHigh: Int = 80,
    val pulse: Int = 65
)
