package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.MessEvent
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.layout_mess.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by betterclever on 4/26/2017.
 */

class MessAdapter(val context: Context, dayNum: Int) : RecyclerView.Adapter<MessAdapter.ViewHolder>() {

    var messItems: RealmResults<MessEvent>

    init {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()

        val day = when(dayNum){
            0 -> "Monday"
            1 -> "Tuesday"
            2 -> "Wednesday"
            3 -> "Thursday"
            4 -> "Friday"
            5 -> "Saturday"
            else -> "Sunday"
        }

        val sharedPref = context.getSharedPreferences("INFO_USR",Context.MODE_PRIVATE)
        val hostelNum = sharedPref.getString("HOSTEL","bh2")

        messItems = realm.where(MessEvent::class.java)
                .equalTo("day",day)
                .equalTo("hostelNum",hostelNum)
                .findAll()

    }


    override fun getItemCount() = messItems.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindMessItem(messItems[position],context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_mess, parent, false))


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val realm = Realm.getDefaultInstance()

        fun bindMessItem(messEvent: MessEvent, context: Context){

            val ch = messEvent.type!![0]

            val colorGenerator = ColorGenerator.MATERIAL
            val color = colorGenerator.getColor(messEvent.type)

            itemView.iconIV.setImageDrawable(TextDrawable.builder().
                    buildRound(ch.toString().capitalize(), color))

            itemView.foodTitleTV.text = messEvent.type
            itemView.timeTV.text = messEvent.startTime!!.toTimeString() + " - " +
                    messEvent.endTime!!.toTimeString()

            var foodString = ""
            for (food in messEvent.foodItems!!){
                foodString += "\u25CF "+ food.stringVal + "\n"
            }
            foodString.removeSuffix("\n")
            itemView.foodTV.text = foodString

            itemView.notificationSwitch.isChecked = messEvent.notificationEnabled

            itemView.notificationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                realm.beginTransaction()
                messEvent.notificationEnabled = isChecked
                realm.commitTransaction()
            }
        }

        fun Long.toTimeString() : String{

            val millis = this.times(1000)
            val df = SimpleDateFormat("hh:mm a")
            df.timeZone = TimeZone.getTimeZone("GMT")
            val str = df.format(millis)

            return str
        }
    }

}