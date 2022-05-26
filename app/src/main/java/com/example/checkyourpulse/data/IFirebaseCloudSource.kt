package com.example.checkyourpulse.data

import com.example.checkyourpulse.domain.model.HealthInfo

interface IFirebaseCloudSource {
    suspend fun getData(): List<HealthInfo>
    suspend fun saveData(data: HealthInfo)
}