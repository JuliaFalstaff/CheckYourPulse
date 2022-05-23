package com.example.checkyourpulse.data


import android.util.Log
import com.example.checkyourpulse.domain.model.HealthInfo
import com.example.checkyourpulse.utils.convertToDataModel
import com.example.checkyourpulse.utils.covertToDocuments
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseCloudSourceImpl(val dataBase: FirebaseFirestore): IFirebaseCloudSource {

    override fun getData(): List<HealthInfo> {
        val healthList = mutableListOf<HealthInfo>()
        dataBase.collection(COLLECTION_NAME)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result) {
                        healthList.add(convertToDataModel(document.data))
                    }
                }
            }
            .addOnFailureListener {
                Log.w(TAG, "Error getting item", it)
            }
        return healthList
    }

    override fun saveData(data: HealthInfo) {
        dataBase.collection(COLLECTION_NAME)
            .add(covertToDocuments(data))
            .addOnSuccessListener {documentReference ->
                Log.d(TAG, "Health Info added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error saving item", e)
            }
    }

    companion object {
        private const val COLLECTION_NAME = "HEALTH"
        private const val TAG = "TAG Firebase"
    }
}