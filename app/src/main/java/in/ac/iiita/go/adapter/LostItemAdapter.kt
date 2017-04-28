package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.LostItem
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by betterclever on 4/29/2017.
 */

class LostItemAdapter: RecyclerView.Adapter<LostItemAdapter.ViewHolder>() {

    private val lostItems = ArrayList<LostItem>()

    override fun getItemCount() = lostItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_lecture, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}