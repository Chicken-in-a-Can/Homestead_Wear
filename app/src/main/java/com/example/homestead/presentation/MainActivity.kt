package com.example.homestead.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.res.painterResource
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
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.example.homestead.R
import com.example.homestead.presentation.theme.HomesteadTheme
import com.example.homestead.presentation.WaterActivity
import com.example.homestead.presentation.WaterNotification
import com.example.homestead.presentation.theme.BrightnessActivity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.appContext = applicationContext
        setContent {
            WearApp()
        }
    }
    companion object {
        lateinit var appContext: Context
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WearApp() {
    val focusRequester = remember {FocusRequester()}
    val listState = rememberScalingLazyListState()
    val coroutineScope = rememberCoroutineScope()
    HomesteadTheme {
        ScalingLazyColumn(
            modifier = Modifier
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        listState.scrollBy(it.verticalScrollPixels)
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable()
                .fillMaxSize()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.Center,
            state = listState,
        ){
            item{
                WaterButton(MainActivity.appContext)
            }
            item{
                WeatherButton(MainActivity.appContext)
            }
            item {
                BrightnessButton(MainActivity.appContext)
            }
            item {
                SettingsButton(MainActivity.appContext)
            }
        }
    }
}

/*
Brightness Start
 */
@Composable
fun BrightnessButton(context: Context){
    Chip(
        modifier = chipModifier(),
        onClick = {
            context.startActivity(Intent(context, BrightnessActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        },
        colors = chipColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = MaterialTheme.colors.primary,
        ),
        label = { BrightnessLabel()},
        icon = { BrightnessIcon()},
    )
}

@Composable
fun BrightnessLabel(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = "Brightness",
    )
}

@Composable
fun BrightnessIcon(){
    Icon(
        painter = painterResource(id = R.drawable.brightness),
        contentDescription = "Brightness Icon",
        modifier = Modifier
            .size(ChipDefaults.LargeIconSize)
            .wrapContentSize(align = Alignment.Center),
    )
}
/*
Brightness End
 */

/*
Water Start
 */

@Composable
fun WaterButton(context: Context){
    Chip(
        modifier = chipModifier(),
        onClick = {
            context.startActivity(Intent(context, WaterActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        },
        colors = chipColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = MaterialTheme.colors.primary,
        ),
        label = { WaterLabel()},
        icon = { WaterIcon()},
    )
}

@Composable
fun WaterLabel(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = "Watering",
    )
}
@Composable
fun WaterIcon(){
    Icon(
        painter = painterResource(id = R.drawable.water),
        contentDescription = "Water Icon",
        modifier = Modifier
            .size(ChipDefaults.LargeIconSize)
            .wrapContentSize(align = Alignment.Center),
    )
}

/*
Water End
 */

/*
Settings Start
 */

@Composable
fun SettingsButton(context: Context){
    Chip(
        modifier = chipModifier(),
        onClick = {
            context.startActivity(Intent(context, SettingsActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        },
        colors = chipColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = MaterialTheme.colors.primary,
        ),
        label = { SettingsLabel() },
        icon = { SettingsIcon()},
    )
}

@Composable
fun SettingsLabel(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = "Settings",
    )
}

@Composable
fun SettingsIcon(){
    Icon(
        painter = painterResource(id = R.drawable.settings),
        contentDescription = "Settings Icon",
        modifier = Modifier
            .size(ChipDefaults.LargeIconSize)
            .wrapContentSize(align = Alignment.Center),
    )
}

/*
Settings End
 */

/*
Weather Start
 */

@Composable
fun WeatherButton(context: Context){
    Chip(
        modifier = chipModifier(),
        onClick = {
            context.startActivity(Intent(context, WeatherActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        },
        colors = chipColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = MaterialTheme.colors.primary,
        ),
        label = { WeatherLabel() },
        icon = { WeatherIcon()},
    )
}

@Composable
fun WeatherLabel(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = "Weather",
    )
}

@Composable
fun WeatherIcon(){
    Icon(
        painter = painterResource(id = R.drawable.weather),
        contentDescription = "Weather Icon",
        modifier = Modifier
            .size(ChipDefaults.LargeIconSize)
            .wrapContentSize(align = Alignment.Center),
    )
}

/*
Weather End
 */

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}

@Composable
fun chipColors() : ChipColors{
    return ChipDefaults.primaryChipColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.background,
        secondaryContentColor = MaterialTheme.colors.secondary,
        iconColor = MaterialTheme.colors.secondaryVariant,
    )
}
fun chipModifier() : Modifier{
    return Modifier.padding(top = 10.dp, bottom = 10.dp)
}