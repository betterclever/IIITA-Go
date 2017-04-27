package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
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

/**
 * Created by betterclever on 4/23/2017.
 */

class LectureAdapter(val context: Context) : RecyclerView.Adapter<LectureAdapter.ViewHolder>() {

    var lectures: RealmResults<Lecture>

    init {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        lectures = realm.where(Lecture::class.java).findAll()
    }


    override fun getItemCount() = lectures.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindLecture(lectures[position],context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_lecture, parent, false))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindLecture(lecture: Lecture, context: Context){

            val ch = lecture.courseId!![1]

            itemView.iconIV.setImageDrawable(TextDrawable.builder().
                    buildRound(ch.toString().capitalize(), Color.RED))

            itemView.lectureTitleTV.text = lecture.courseId
            itemView.lectureTimeTV.text = lecture.startTime.toString()
            //itemView.facultyTV.text = lecture.teachersRef!![0].name
            itemView.locationTV.text = lecture.location
            itemView.notificationSwitch.isChecked = lecture.notificationEnabled
        }
    }
}