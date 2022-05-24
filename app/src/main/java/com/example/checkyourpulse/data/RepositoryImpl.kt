package com.example.checkyourpulse.data

import com.example.checkyourpulse.domain.model.HealthInfo
import com.example.checkyourpulse.domain.repository.IRepository

class RepositoryImpl(private val cloudSource: IFirebaseCloudSource): IRepository {
    override suspend fun getData(): List<HealthInfo> {
        return cloudSource.getData()
    }

    override suspend fun saveData(data: HealthInfo) {
        cloudSource.saveData(data)
    }
}