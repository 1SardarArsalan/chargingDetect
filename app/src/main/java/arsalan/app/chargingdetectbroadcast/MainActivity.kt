package arsalan.app.chargingdetectbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.RingtoneManager
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var default=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context:Context
        val tvBtryLevel = findViewById<TextView>(R.id.tvBtryLevel)
        /*val passData = Intent(this,batteryStateDetect::class.java)
       // passData.putExtra("tvLevel","tvBtryLevel")*/


     // val ringtone = RingtoneManager.getRingtone(applicationContext , RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))

        val broadcastReceiverLevel: BroadcastReceiver = object: BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {

                val  batteryLevel = p1?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)
                tvBtryLevel.setText("%"+batteryLevel.toString())

                /*if (batteryLevel != null && default==0) {
                    if (batteryLevel > 30)
                        ringtone.play()
                }*/

}
        }

        val batteryState = batteryStateDetect()
        registerReceiver(batteryState, IntentFilter(Intent.ACTION_POWER_CONNECTED))
        registerReceiver(batteryState, IntentFilter(Intent.ACTION_POWER_DISCONNECTED))

          registerReceiver(broadcastReceiverLevel, IntentFilter(Intent.ACTION_BATTERY_CHANGED))



    }


}