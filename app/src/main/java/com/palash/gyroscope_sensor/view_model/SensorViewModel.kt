package com.palash.gyroscope_sensor.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.palash.gyroscope_sensor.respository.SensorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(private val sensorRepository: SensorRepository) : ViewModel() {

    val gyroscopeData: LiveData<Triple<Float, Float, Float>> = sensorRepository.gyroscopeData

    fun startListening() {
        sensorRepository.startListening()
    }

    fun stopListening() {
        sensorRepository.stopListening()
    }
}