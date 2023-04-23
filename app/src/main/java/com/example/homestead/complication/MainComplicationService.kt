package com.example.homestead.complication

import android.content.Intent
import android.content.ServiceConnection
import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.ComplicationType
import androidx.wear.watchface.complications.data.PlainComplicationText
import androidx.wear.watchface.complications.data.ShortTextComplicationData
import androidx.wear.watchface.complications.datasource.ComplicationRequest
import androidx.wear.watchface.complications.datasource.SuspendingComplicationDataSourceService
import java.time.Duration
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.Calendar
import com.example.homestead.presentation.MainActivity

/**
 * Skeleton for complication data source that returns short text.
 */
class MainComplicationService : SuspendingComplicationDataSourceService() {
    override fun bindService(service: Intent?, conn: ServiceConnection, flags: Int): Boolean {
        return super.bindService(Intent(applicationContext, MainActivity::class.java), conn, flags)
    }

    override fun getPreviewData(type: ComplicationType): ComplicationData? {
        val timezone = ZoneId.of("America/Matamoros")
        val now = ZonedDateTime.now(timezone).truncatedTo(ChronoUnit.MINUTES)
        val targetTime = LocalTime.of(8, 0)
        val targetDate = if(now.toLocalTime() <= targetTime) now.toLocalDate()
        else now.toLocalDate().plusDays(1)
        val then = ZonedDateTime.of(targetDate, targetTime, timezone)
        val return_string = if (Duration.between(now, then).toHours() >= 1) Duration.between(now, then).toHours().toString() + "H"
            else (Duration.between(now, then).toMinutes() % 60).toString()
        return createComplicationData(return_string, "Time till Water")
    }

    override suspend fun onComplicationRequest(request: ComplicationRequest): ComplicationData {
        val timezone = ZoneId.of("America/Matamoros")
        val now = ZonedDateTime.now(timezone).truncatedTo(ChronoUnit.MINUTES)
        val targetTime = LocalTime.of(8, 0)
        val targetDate = if(now.toLocalTime() <= targetTime) now.toLocalDate()
        else now.toLocalDate().plusDays(1)
        val then = ZonedDateTime.of(targetDate, targetTime, timezone)
        val return_string = Duration.between(now, then).toHours().toString() + "H " + (Duration.between(now, then).toMinutes() % 60).toString() + "M"
        return createComplicationData(return_string, "Time till Water")
    }

    private fun createComplicationData(text: String, contentDescription: String) =
        ShortTextComplicationData.Builder(
            text = PlainComplicationText.Builder(text).build(),
            contentDescription = PlainComplicationText.Builder(contentDescription).build()
        ).build()
}