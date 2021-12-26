package com.rizkyhamdana.yorental.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rizkyhamdana.yorental.data.DataResponse
import com.rizkyhamdana.yorental.util.Const

class AppRepository {

    private val listKendaraan = MutableLiveData<List<DataResponse>>()

    fun getData(): LiveData<List<DataResponse>>{
        val kendaraans = ArrayList<DataResponse>()
        val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
        firebaseDb.getReference("kendaraan").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val kendaraan = i.getValue(DataResponse::class.java) as DataResponse
                    kendaraans.add(kendaraan)
                    listKendaraan.postValue(kendaraans)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error: ", error.message)
            }
        })
        return listKendaraan
    }

    fun hitungBiaya(biaya: Int, count: Int ): Int{
        return count*biaya
    }
}