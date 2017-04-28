package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.Course
import `in`.ac.iiita.go.models.Lecture
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amulyakhare.textdrawable.TextDrawable
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.layout_lecture.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by betterclever on 4/23/2017.
 */

class LectureAdapter(val context: Context, dayNum: Int) : RecyclerView.Adapter<LectureAdapter.ViewHolder>() {

    var lectures: RealmResults<Lecture>

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
        val semester = sharedPref.getString("SEMESTER","4")
        val section = sharedPref.getString("SECTION","A")


        lectures = realm.where(Lecture::class.java)
                .equalTo("day",day)
                .like("id", "????$semester*")
                .equalTo("section",section)
                .findAll()
    }


    override fun getItemCount() = lectures.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindLecture(lectures[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_lecture, parent, false))


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val realm = Realm.getDefaultInstance()

        fun bindLecture(lecture: Lecture){

            val ch = lecture.courseId!![1]
            itemView.iconIV.setImageDrawable(TextDrawable.builder().
                    buildRound(ch.toString().capitalize(), Color.RED))

            val course = realm.where(Course::class.java)
                    .equalTo("id",lecture.courseId).findFirst()

            itemView.lectureTitleTV.text = course.name
            itemView.lectureTimeTV.text = lecture.startTime!!.toTimeString() + " - " +
                    lecture.endTime!!.toTimeString()

            itemView.locationTV.text = lecture.location
            itemView.notificationSwitch.isChecked = lecture.notificationEnabled

            var teachersStr = ""
            for (teacher in lecture.teachersRef!!){
                teachersStr += "\u25CF  " + teacher.name + "\n"
            }

            itemView.facultyTV.text = teachersStr

            itemView.notificationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                realm.beginTransaction()
                lecture.notificationEnabled = isChecked
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