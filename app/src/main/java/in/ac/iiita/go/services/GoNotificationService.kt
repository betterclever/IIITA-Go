package `in`.ac.iiita.go.services

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.Lecture
import `in`.ac.iiita.go.models.MessEvent
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.support.v7.app.NotificationCompat
import io.realm.Realm
import java.util.*


/**
 * Created by betterclever on 4/28/2017.
 */

class GoNotificationService : Service(){

    private var broadCastReceiver: BroadcastReceiver? = null
    private var realm : Realm? = null
    private val filter : IntentFilter = IntentFilter(Intent.ACTION_TIME_TICK)

    init {
        filter.matchAction(Intent.ACTION_TIME_CHANGED)
        filter.matchAction(Intent.ACTION_TIMEZONE_CHANGED)
        filter.matchAction(Intent.ACTION_DATE_CHANGED)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        broadCastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {

                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val secondsOfDay = hour*3600 + minute*60

                val dayNum = calendar.get(Calendar.DAY_OF_WEEK)
                val day = when(dayNum){
                    Calendar.SUNDAY -> "Sunday"
                    Calendar.MONDAY -> "Monday"
                    Calendar.TUESDAY -> "Tuesday"
                    Calendar.WEDNESDAY -> "Wednesday"
                    Calendar.THURSDAY -> "Thursday"
                    Calendar.FRIDAY -> "Friday"
                    else -> "Saturday"

                }

                val nearLectures = realm!!.where(Lecture::class.java)
                        .lessThan("startTime",secondsOfDay+610)
                        .greaterThan("startTime",secondsOfDay+530)
                        .equalTo("notificationEnabled",true)
                        .equalTo("day",day)
                        .findAll()

                val nearMessEvent = realm!!.where(MessEvent::class.java)
                        .lessThan("startTime",secondsOfDay+610)
                        .greaterThan("startTime",secondsOfDay+530)
                        .equalTo("notificationEnabled",true)
                        .equalTo("day",day)
                        .findAll()

                val mNotifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                for((i, lecture) in nearLectures.withIndex()){

                    val builder = NotificationCompat.Builder(this@GoNotificationService)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(lecture.courseId+ " Lecture Starting in 10 minutes")
                            .setContentText("Location: " +lecture.location)

                    val mNotificationId = secondsOfDay+(i)
                    mNotifyMgr.notify(mNotificationId, builder.build())
                }

                for((i, messEvent) in nearMessEvent.withIndex()){

                    var foodString = ""
                    for (food in messEvent.foodItems!!){
                        foodString += "\u25CF "+ food.stringVal + "\n"
                    }

                    val builder = NotificationCompat.Builder(this@GoNotificationService)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(messEvent.type + " in 10 minutes ")
                            .setContentText(foodString)

                    val mNotificationId = secondsOfDay+(i)
                    mNotifyMgr.notify(mNotificationId, builder.build())
                }
            }
        }

        registerReceiver(broadCastReceiver,filter)
    }
}