package com.example.checkyourpulse.domain

sealed class AppState {
    data class Success<out T>(val dataHealth: T) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}