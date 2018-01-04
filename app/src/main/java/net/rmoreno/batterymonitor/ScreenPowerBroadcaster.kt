package net.rmoreno.batterymonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ScreenPowerBroadcaster : BroadcastReceiver() {
    private var screenOnTime: Long? = null
    private var screenOffTime: Long? = null

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            screenOffTime = System.currentTimeMillis()
            Toast.makeText(context, "Timer started", Toast.LENGTH_LONG).show()

        } else if (intent.action == Intent.ACTION_SCREEN_ON) {
            //screenOff = false
            screenOnTime = System.currentTimeMillis()
        }
    }

}

