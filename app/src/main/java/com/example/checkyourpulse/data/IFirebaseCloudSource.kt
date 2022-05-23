package com.example.checkyourpulse.data

import com.example.checkyourpulse.domain.model.HealthInfo

interface IFirebaseCloudSource {
    fun getData(): List<HealthInfo>
    fun saveData(data: HealthInfo)
}