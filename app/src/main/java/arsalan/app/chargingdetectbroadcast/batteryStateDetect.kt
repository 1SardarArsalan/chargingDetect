package arsalan.app.chargingdetectbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat

/*val  batteryLevel = p1?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)
tvBtryResult.setText("%"+batteryLevel.toString())*/

class batteryStateDetect : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {

        var  levels = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)

        Toast.makeText(context, "level is : "+levels, Toast.LENGTH_SHORT).show()


        if (intent.action == Intent.ACTION_POWER_CONNECTED) {
           // Toast.makeText(context, "Power Connected", Toast.LENGTH_SHORT).show()
            val serviceIntent = Intent(context, ExampleService::class.java)
           // serviceIntent.putExtra("inputExtra", batryLevels)
            ContextCompat.startForegroundService(context!!, serviceIntent)
            Log.d("abc","service running")


        } else if (intent.action == Intent.ACTION_POWER_DISCONNECTED) {
            Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show()
            val serviceIntent = Intent(context, ExampleService::class.java)
            if (context != null) {
                context.stopService(serviceIntent)
            }

        }
    }
}
