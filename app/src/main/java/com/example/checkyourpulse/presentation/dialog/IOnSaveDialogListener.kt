package com.example.checkyourpulse.presentation.dialog

import com.example.checkyourpulse.domain.model.HealthInfo

interface IOnSaveDialogListener {
    fun onSave(health: HealthInfo)
}