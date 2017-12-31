package net.rmoreno.batterymonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val sb = StringBuilder()
        sb.append("Action: " + intent.action + "\n")
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n")
        val log = sb.toString()
        Toast.makeText(context, log, Toast.LENGTH_LONG).show()
    }
}
