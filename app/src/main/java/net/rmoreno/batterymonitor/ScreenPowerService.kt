package net.rmoreno.batterymonitor

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.content.IntentFilter

class ScreenPowerService : IntentService("Screen Power Service") {

//    val ACTION_SCREEN_ON = ScreenPowerService::class.java.canonicalName as String + ".action.SCREEN_ON"
//
//    val ACTION_SCREEN_OFF = ScreenPowerService::class.java.canonicalName as String + ".action.SCREEN_OFF"

    override fun onCreate() {
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        val mReceiver = ScreenReceiver()
        registerReceiver(mReceiver, filter)
    }

    override fun onHandleIntent(intent: Intent?) {
        TODO("not implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_NOT_STICKY
    }

}
