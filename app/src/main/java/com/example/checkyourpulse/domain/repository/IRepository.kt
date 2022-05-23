package com.example.checkyourpulse.domain.repository

import com.example.checkyourpulse.domain.model.HealthInfo

interface IRepository {
    fun getData(): List<HealthInfo>
    fun saveData(data: HealthInfo)
}