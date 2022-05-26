package com.example.checkyourpulse.domain.repository

import com.example.checkyourpulse.domain.model.HealthInfo

interface IRepository {
    suspend fun getData(): List<HealthInfo>
    suspend fun saveData(data: HealthInfo)
}