package net.rmoreno.batterymonitor

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var time: TextView? = null
    var amount: TextView? = null
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val intent = Intent(this@MainActivity, ScreenPowerService::class.java)
        startService(intent)

        time = findViewById(R.id.time) as TextView
        amount = findViewById(R.id.amount) as TextView
        prefs = getSharedPreferences(getString(R.string.time_pref), 0)
    }

    fun displayTime() {
        val lastTimeOn = prefs?.getLong(getString(R.string.last_time_on), 0)
        val unlockCount = prefs?.getInt(getString(R.string.unlock_count), 0)
        time!!.text = lastTimeOn.toString()
        amount!!.text = unlockCount.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        displayTime()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
