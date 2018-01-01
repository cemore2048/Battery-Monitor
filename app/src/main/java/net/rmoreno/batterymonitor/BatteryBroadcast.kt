package net.rmoreno.batterymonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryBroadcast : BroadcastReceiver() {

    var wasScreenOn: Boolean? = null

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            // TODO: start timing here
            wasScreenOn = false
        } else if (intent.action == Intent.ACTION_SCREEN_ON) {
            // TODO: end timing here
            wasScreenOn = true
        }
    }
}
