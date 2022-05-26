package com.example.checkyourpulse.di

import com.example.checkyourpulse.data.FirebaseCloudSourceImpl
import com.example.checkyourpulse.data.IFirebaseCloudSource
import com.example.checkyourpulse.data.RepositoryImpl
import com.example.checkyourpulse.domain.repository.IRepository
import com.example.checkyourpulse.presentation.MainFragment
import com.example.checkyourpulse.presentation.MainViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }
    single<IFirebaseCloudSource> { FirebaseCloudSourceImpl(dataBase = get()) }
    single<IRepository> { RepositoryImpl(cloudSource = get()) }
}

val mainScreen = module {
    scope<MainFragment> {
        viewModel { MainViewModel(repo = get()) }
    }
}