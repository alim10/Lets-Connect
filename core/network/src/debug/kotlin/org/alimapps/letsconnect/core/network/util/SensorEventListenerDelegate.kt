package org.alimapps.letsconnect.core.network.util
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.ref.WeakReference
import kotlin.math.sqrt

class SensorEventListenerDelegate: LifecycleObserver,
    LifecycleEventObserver {

    private var mFragment: WeakReference<Fragment>? = null
    private var sensorManager: SensorManager? = null
    private var sensorEvent: SensorEventListener? = null
    private var accelerometer: Sensor? = null
    private var shakeTime: Long = 0
    private var shakeDetected: Boolean = false

    fun initShakerDeveloperOption(fragment: WeakReference<Fragment>): Flow<Boolean> {
        fragment.get()?.viewLifecycleOwner?.lifecycle?.addObserver(this)
        mFragment = fragment
        sensorManager = mFragment?.get()?.requireContext()?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        return callbackFlow {
            sensorEvent = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    if (shakeDetected) return
                    event?.let {
                        val x = it.values[0]
                        val y = it.values[1]
                        val z = it.values[2]

                        val gX = x / SensorManager.GRAVITY_EARTH
                        val gY = y / SensorManager.GRAVITY_EARTH
                        val gZ = z / SensorManager.GRAVITY_EARTH

                        val gForce = sqrt((gX * gX + gY * gY + gZ * gZ).toDouble()).toFloat()

                        if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                            val currentTime = System.currentTimeMillis()
                            if (currentTime - shakeTime > SHAKE_SLOP_TIME_MS) {
                                shakeTime = currentTime
                                shakeDetected = true
                                // Trigger the BottomSheet when shake is detected
                                trySend(true)
                            }
                        }
                    }
                }
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }
            awaitClose { close() }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {}
            Lifecycle.Event.ON_PAUSE -> {
                sensorManager?.unregisterListener(sensorEvent)
            }
            Lifecycle.Event.ON_START -> {}
            Lifecycle.Event.ON_RESUME -> {
                shakeDetected = false
                accelerometer?.also { sensor ->
                    sensorManager?.registerListener(sensorEvent, sensor, SensorManager.SENSOR_DELAY_UI)
                }
            }
            Lifecycle.Event.ON_STOP -> {}
            Lifecycle.Event.ON_DESTROY -> {
                sensorManager?.unregisterListener(sensorEvent)
                mFragment = null
                sensorManager = null
                accelerometer = null
                sensorEvent = null
            }
            else -> {}
        }
    }

    fun unregisterListener() {
        sensorManager?.unregisterListener(sensorEvent)
    }

    companion object {
        const val SHAKE_THRESHOLD_GRAVITY = 2.7F
        const val SHAKE_SLOP_TIME_MS = 800
    }
}

