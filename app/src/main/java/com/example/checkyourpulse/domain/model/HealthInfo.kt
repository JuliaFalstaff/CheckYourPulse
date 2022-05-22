package com.example.checkyourpulse.domain.model

data class HealthInfo(
    val date: String,
    val time: String,
    val pressureLow: Int,
    val pressureHigh: Int,
    val pulse: Int
)
