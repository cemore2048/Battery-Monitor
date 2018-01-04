package net.rmoreno.batterymonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ScreenReceiver : BroadcastReceiver() {

    private var screenOff: Boolean = false

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            screenOff = true
        } else if (intent.action == Intent.ACTION_SCREEN_ON) {
            screenOff = false
        }
    }

}

