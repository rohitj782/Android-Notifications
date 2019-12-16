package com.example.android.eggtimernotifications

import android.app.Notification
import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService: FirebaseMessagingService(){

    override fun onNewToken(token: String?) {
        Log.i("TOKEN", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
    }

    // [START receive_message]
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.i("MESSAGE", "From: ${remoteMessage?.from}")

        // TODO: Step 3.5 check messages for data
        // Check if message contains a data payload.
        remoteMessage?.data?.let {
            Log.i("MESSAGE", "Message data payload: " + remoteMessage.data)
        }

        // TODO: Step 3.6 check messages for notification and call sendNotification
        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
            sendNotification(it.body!!)
        }

    }
    // [END receive_message]
    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }

}