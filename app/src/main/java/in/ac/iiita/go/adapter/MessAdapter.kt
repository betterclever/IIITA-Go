package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.MessEvent
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amulyakhare.textdrawable.TextDrawable
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.layout_mess.view.*

/**
 * Created by betterclever on 4/26/2017.
 */

class MessAdapter(val context: Context) : RecyclerView.Adapter<MessAdapter.ViewHolder>() {

    var messItems: RealmResults<MessEvent>

    init {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        messItems = realm.where(MessEvent::class.java).findAll()
    }


    override fun getItemCount() = messItems.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindMessItem(messItems[position],context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_mess, parent, false))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindMessItem(messEvent: MessEvent, context: Context){
            itemView.notificationSwitch.isChecked = messEvent.notificationEnabled
        }
    }
}