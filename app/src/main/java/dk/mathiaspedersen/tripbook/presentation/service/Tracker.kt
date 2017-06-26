package dk.mathiaspedersen.tripbook.presentation.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import dk.mathiaspedersen.tripbook.R

class Tracker: Service() {

    override fun onCreate() {
        super.onCreate()
    }

    private val ONGOING_NOTIFICATION_ID: Int = 19

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificationIntent = Intent(this, Tracker::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = Notification.Builder(this)
                .setContentTitle("TripBook")
                .setContentText("Tracking")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build()

        startForeground(ONGOING_NOTIFICATION_ID, notification)

        return Service.START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}