package com.example.checkyourpulse.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.checkyourpulse.domain.AppState
import com.example.checkyourpulse.domain.model.HealthInfo
import com.example.checkyourpulse.domain.repository.IRepository
import kotlinx.coroutines.*

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    val repo: IRepository): ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve

    private var job: Job? = null

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    fun loadData() {
        liveDataToObserve.postValue(AppState.Loading)
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            liveDataToObserve.postValue(AppState.Success(repo.getData()))
        }
    }

    fun saveData(healthInfo: HealthInfo) {
        viewModelCoroutineScope.launch {
            repo.saveData(healthInfo)
            loadData()
        }
    }

    private fun handleError(error: Throwable) {
        liveDataToObserve.postValue(AppState.Error(error))
    }
}