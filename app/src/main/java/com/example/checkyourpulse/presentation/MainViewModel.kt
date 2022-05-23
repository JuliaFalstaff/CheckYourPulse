package com.example.checkyourpulse.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.checkyourpulse.domain.AppState
import com.example.checkyourpulse.domain.repository.IRepository

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    val repo: IRepository): ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve




}