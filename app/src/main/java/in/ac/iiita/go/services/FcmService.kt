package `in`.ac.iiita.go.services

import android.app.NotificationManager
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import `in`.ac.iiita.go.R
import android.content.Context


/**
 * Created by affan on 28/4/17.
 */


class FcmService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage!!.notification != null) {

            Log.i(TAG, "Notification: " + remoteMessage.notification.body)

            val mBuilder = NotificationCompat.Builder(this)
                    .setColor(Color.BLUE)
                    .setSmallIcon(R.drawable.ic_noti)
                    .setContentTitle(remoteMessage.notification.title)
                    .setContentText(remoteMessage.notification.body)
                    .setStyle(NotificationCompat.BigTextStyle()
                            .bigText(remoteMessage.notification.body))

            val mNotificationId = 1
            val mNotifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            mNotifyMgr.notify(mNotificationId, mBuilder.build())
        }

        if (remoteMessage.data != null) {
            Log.i(TAG, "Data: " + remoteMessage.data)
        }
    }

    override fun onMessageSent(s: String?) {
        super.onMessageSent(s)
        Log.d(TAG, "Sent: " + s!!)
    }

    companion object {

        private val TAG = FcmService::class.java.simpleName
    }
}
