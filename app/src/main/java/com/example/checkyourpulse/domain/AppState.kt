package com.example.checkyourpulse.domain

import com.example.checkyourpulse.domain.model.HealthInfo

sealed class AppState {
    data class Success(val dataHealth: HealthInfo) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}