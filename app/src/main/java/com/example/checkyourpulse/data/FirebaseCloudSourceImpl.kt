package com.example.checkyourpulse.data


import android.util.Log
import com.example.checkyourpulse.domain.model.HealthInfo
import com.example.checkyourpulse.utils.DATE
import com.example.checkyourpulse.utils.convertToDataModel
import com.example.checkyourpulse.utils.covertToDocuments
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.tasks.await

class FirebaseCloudSourceImpl(val dataBase: FirebaseFirestore): IFirebaseCloudSource {

    override suspend fun getData(): List<HealthInfo> {
        val healthList = mutableListOf<HealthInfo>()
        dataBase.collection(COLLECTION_NAME).orderBy(DATE, Query.Direction.ASCENDING)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result) {
                        healthList.add(convertToDataModel(document.data))
                        Log.d(TAG, "isSuccessful add $healthList")
                    }
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Error getting item", it)
            }.await()
        return healthList
    }

    override suspend fun saveData(data: HealthInfo) {
        dataBase.collection(COLLECTION_NAME)
            .add(covertToDocuments(data))
            .addOnSuccessListener {documentReference ->
                Log.d(TAG, "Health Info added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "Error saving item", e)
            }
    }

    companion object {
        private const val COLLECTION_NAME = "HEALTH"
        private const val TAG = "TAG Firebase"
    }
}