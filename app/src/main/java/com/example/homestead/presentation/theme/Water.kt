package com.example.homestead.presentation


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
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
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.example.homestead.R
import com.example.homestead.presentation.theme.HomesteadTheme
import java.net.URL
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.TimeZone

class WaterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WaterActivity.appContext = applicationContext
        setContent {
            WaterScreen()
        }
    }
    companion object {
        lateinit var appContext: Context
    }
}

@Composable
fun WaterScreen() {
    HomesteadTheme {
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.Center,
        ){
            item{
                NotificationLabel()
            }
        }
    }
}

/*
Brightness Start
 */

@Composable
fun NotificationLabel(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = timeTilWater(),
    )
}

fun latestRequest(id: String){
    val response = URL("https://homestead.martinstemworks.com/").readText()
}

fun timeTilWater(): String{
    val timezone = ZoneId.of("America/Matamoros")
    val now = ZonedDateTime.now(timezone).truncatedTo(ChronoUnit.MINUTES)
    val targetTime = LocalTime.of(8, 0)
    val targetDate = if(now.toLocalTime() <= targetTime) now.toLocalDate()
        else now.toLocalDate().plusDays(1)
    val then = ZonedDateTime.of(targetDate, targetTime, timezone)
    val food = "Carrots"
    val return_string = "Water " + food + " in\n" + Duration.between(now, then).toHours().toString() + " Hours, " + (Duration.between(now, then).toMinutes() % 60).toString() + " Minutes"
    return return_string
}

/*
Brightness End
 */

