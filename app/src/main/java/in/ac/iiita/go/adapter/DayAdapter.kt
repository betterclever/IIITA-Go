package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.Lecture
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by betterclever on 4/23/2017.
 */

class DayAdapter(val context: Context) : RecyclerView.Adapter<DayAdapter.ViewHolder>() {

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

        }
    }
}