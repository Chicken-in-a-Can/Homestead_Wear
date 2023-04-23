package com.example.homestead.presentation.theme



import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipColors
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.ChipDefaults.chipColors
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.example.homestead.R
import com.example.homestead.presentation.MainActivity
import com.example.homestead.presentation.WaterNotification
import com.example.homestead.presentation.chipModifier
import com.example.homestead.presentation.theme.HomesteadTheme

var brightness_str: MutableState<Float> = mutableStateOf(0.0F)

class BrightnessActivity : ComponentActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var brightness: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BrightnessActivity.appContext = applicationContext
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        setContent {
            BrightnessScreen()
        }
    }
    override fun onSensorChanged(event: SensorEvent){
        if (event != null) {
            brightness_str.value = event.values[0]
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        brightness_str.value = brightness_str.value
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
    companion object {
        lateinit var appContext: Context
    }
}

@Composable
fun BrightnessScreen() {
    HomesteadTheme {
        Box(Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                BrightnessLabel()
            }
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = (brightness_str.value / 2000),
                strokeWidth = 10.dp,
                indicatorColor = MaterialTheme.colors.primary,
                trackColor = MaterialTheme.colors.secondaryVariant,
            )
        }
    }
}

@Composable
fun BrightnessLabel(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = brightness_str.value.toString()
    )
}
