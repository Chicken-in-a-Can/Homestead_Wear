package com.example.homestead.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.provider.SyncStateContract
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.homestead.R

fun WaterNotification(plants_to_water: String, context: Context){
    var channel_id = "WATER_PLANT"
    var channel = NotificationChannel(channel_id, "WaterChannel", NotificationManager.IMPORTANCE_MIN)
    var notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if(notificationManager != null){
        notificationManager.createNotificationChannel(channel);
    }
    var build: NotificationCompat.Builder = NotificationCompat
        .Builder(context, channel_id)
        .setSmallIcon(R.mipmap.ic_launcher_round)
        .setContentTitle("Water your plants")
        .setContentText(plants_to_water)
        .setPriority(1)
    return NotificationManagerCompat.from(context).notify(1, build.build())
}