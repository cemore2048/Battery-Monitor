package net.rmoreno.batterymonitor

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class ScreenPowerService : Service() {
//    val ACTION_SCREEN_ON = ScreenPowerService::class.java.canonicalName as String + ".action.SCREEN_ON"
//
//    val ACTION_SCREEN_OFF = ScreenPowerService::class.java.canonicalName as String + ".action.SCREEN_OFF"

    var receiver: BroadcastReceiver? = null

    override fun onCreate() {
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_DREAMING_STARTED)
        filter.addAction(Intent.ACTION_DREAMING_STOPPED)
        receiver = ScreenPowerBroadcaster()
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        //Start new intent service that actually takes into consideration time.

        if (receiver != null) {
            unregisterReceiver(receiver)
        }
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_STICKY
    }

}
