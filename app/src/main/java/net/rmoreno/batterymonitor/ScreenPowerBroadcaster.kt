package net.rmoreno.batterymonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class ScreenPowerBroadcaster : BroadcastReceiver() {
    private var screenOnTime: Long? = null
    private var screenOffTime: Long? = null

    override fun onReceive(context: Context, intent: Intent) {
        val editor : SharedPreferences.Editor = context.getSharedPreferences(context.getString(R.string.time_pref), 0).edit()
        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            val currTime = System.currentTimeMillis()
            val prefs = context.getSharedPreferences(context.getString(R.string.time_pref), 0)
            val timeOn: Long = prefs.getLong(context.getString(R.string.time_on), 0)
            val totalTime : Long = currTime - timeOn

            editor.putLong(context.getString(R.string.last_time_on), totalTime).apply()

        } else if (intent.action == Intent.ACTION_SCREEN_ON) {
            //screenOff = false
            screenOnTime = System.currentTimeMillis()
            screenOnTime?.let {editor.putLong(context.getString(R.string.time_on), screenOnTime!!).apply()}
        }
    }
}

