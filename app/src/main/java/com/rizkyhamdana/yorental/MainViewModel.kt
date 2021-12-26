package com.rizkyhamdana.yorental

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizkyhamdana.yorental.data.DataResponse
import com.rizkyhamdana.yorental.repository.AppRepository

class MainViewModel: ViewModel() {

    private val appRepository: AppRepository = AppRepository()

    fun getData(): LiveData<List<DataResponse>> = appRepository.getData()

    fun hitungBiaya(biaya: Int, count: Int): Int = appRepository.hitungBiaya(biaya, count)

}