package net.rmoreno.batterymonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class ScreenPowerBroadcaster : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val editor : SharedPreferences.Editor = context.getSharedPreferences(context.getString(R.string.time_pref), 0).edit()
        val prefs = context.getSharedPreferences(context.getString(R.string.time_pref), 0)

        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            val currTime = System.currentTimeMillis()
            val timeOn: Long = prefs.getLong(context.getString(R.string.time_on), 0)
            val lastOnTime : Long = currTime - timeOn

            editor.putLong(context.getString(R.string.last_time_on), lastOnTime).apply()

        } else if (intent.action == Intent.ACTION_SCREEN_ON) {
            //screenOff = false
            val screenOnTime = System.currentTimeMillis()
            val screenOnCount = prefs.getInt(context.getString(R.string.unlock_count), 0) + 1

            screenOnCount.let{editor.putInt(context.getString(R.string.unlock_count), screenOnCount)}
            screenOnTime.let {editor.putLong(context.getString(R.string.time_on), screenOnTime).apply()}
        }
    }
}

