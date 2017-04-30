package `in`.ac.iiita.go

import `in`.ac.iiita.go.api.GoService
import `in`.ac.iiita.go.services.GoNotificationService
import android.app.Application
import org.jetbrains.anko.startService

/**
 * Created by betterclever on 4/28/2017.
 */

class IIITAGoApp(): Application(){

    override fun onCreate() {
        super.onCreate()
        startService<GoNotificationService>()

        val go = GoService(this)
        go.storeData()
    }
}