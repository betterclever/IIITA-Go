package `in`.ac.iiita.go

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
    }
}