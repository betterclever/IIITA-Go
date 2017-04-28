package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.LostItem
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_lost.view.*

/**
 * Created by betterclever on 4/29/2017.
 */

class LostItemAdapter: RecyclerView.Adapter<LostItemAdapter.ViewHolder>() {

    private val lostItems = ArrayList<LostItem>()

    override fun getItemCount() = lostItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_lecture, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItem(lostItem : LostItem){
            itemView.lostTitleTV.text = lostItem.name
            itemView.descriptionTV.text = lostItem.description
            itemView.ownerNameTV.text = lostItem.ownerName
            itemView.phoneTV.text = lostItem.ownerPhone.toString()
            itemView.extraTV.text = lostItem.ownerExtraDetail
        }
    }
}