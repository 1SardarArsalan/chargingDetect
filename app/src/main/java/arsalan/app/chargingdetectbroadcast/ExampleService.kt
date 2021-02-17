package arsalan.app.chargingdetectbroadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.RingtoneManager
import android.os.BatteryManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import kotlin.math.log

class ExampleService : Service() {
    private val CHANNEL_ID = "ForegroundService Kotlin"


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val ringtone = RingtoneManager.getRingtone(applicationContext , RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))

        ringtone.play()
        //do heavy work on a background thread
        //Log.d("shehroz",input.toString())

        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Alarm Set")
            .setContentText("charger connected")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        //stopSelf();
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        val ringtone = RingtoneManager.getRingtone(applicationContext , RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))

        ringtone.stop()

        super.onDestroy()
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }
}