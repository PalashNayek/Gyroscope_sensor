package com.palash.gyroscope_sensor.respository

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class SensorRepository @Inject constructor(private val sensorManager: SensorManager) {

    private val _gyroscopeData = MutableLiveData<Triple<Float, Float, Float>>()
    val gyroscopeData: LiveData<Triple<Float, Float, Float>> = _gyroscopeData

    private val gyroscopeListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                if (it.sensor.type == Sensor.TYPE_GYROSCOPE) {
                    _gyroscopeData.postValue(Triple(it.values[0], it.values[1], it.values[2]))
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // No action needed
        }
    }

    fun startListening() {
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.also { gyroscope ->
            sensorManager.registerListener(gyroscopeListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(gyroscopeListener)
    }
}