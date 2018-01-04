package net.rmoreno.batterymonitor

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class ScreenPowerService : IntentService("Screen Power Service") {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        val mReceiver = ScreenReceiver()
        registerReceiver(mReceiver, filter)
    }

    override fun onHandleIntent(p0: Intent?) {
        TODO("not implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_NOT_STICKY
    }

}
