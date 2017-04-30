package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.FoundItem
import `in`.ac.iiita.go.models.LostItem
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import kotlinx.android.synthetic.main.layout_found.view.*
import kotlinx.android.synthetic.main.layout_lost.view.*

/**
 * Created by betterclever on 4/29/2017.
 */

class LostItemAdapter: RecyclerView.Adapter<LostItemAdapter.ViewHolder>() {

    private val lostItems = ArrayList<LostItem>()

    override fun getItemCount() = lostItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_lost, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) =
            holder!!.bindItem(lostItems[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItem(lostItem : LostItem){

            val colorGenerator = ColorGenerator.MATERIAL
            val color = colorGenerator.getColor(lostItem.name)

            itemView.iconlIV.setImageDrawable(TextDrawable.builder().
                    buildRound(lostItem.name[0].toString().capitalize(), color))

            itemView.lostTitleTV.text = lostItem.name
            itemView.descriptionTV.text = "Description: "+ lostItem.description
            itemView.ownerNameTV.text = "Owner Name: "+ lostItem.ownerName
            itemView.phoneTV.text = "Owner Phone: "+ lostItem.ownerPhone.toString()
            itemView.extraTV.text = "Date and Time:"+ lostItem.extraDetail
        }
    }

    fun addItems(lostItems : List<LostItem>){
        this.lostItems.clear()
        this.lostItems.addAll(lostItems)
        notifyDataSetChanged()
    }
}

class FoundItemAdapter: RecyclerView.Adapter<FoundItemAdapter.ViewHolder>() {

    private val foundItems = ArrayList<FoundItem>()

    override fun getItemCount() = foundItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_found, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) =
            holder!!.bindItem(foundItems[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItem(item: FoundItem){

            val colorGenerator = ColorGenerator.MATERIAL
            val color = colorGenerator.getColor(item.name)

            itemView.iconfIV.setImageDrawable(TextDrawable.builder().
                    buildRound(item.name[0].toString().capitalize(), color))

            itemView.foundTitleTV.text = item.name
            itemView.descriptionFoundTV.text = "Description: "+ item.description
            itemView.founderNameTV.text = "Founder Name: "+ item.founderName
            itemView.founderPhoneTV.text = "Founder Phone: "+ item.founderPhone.toString()
            itemView.extraFoundTV.text = "Date and Time: "+  item.extraDetail
        }
    }

    fun addItems(foundItems: List<FoundItem>){
        this.foundItems.clear()
        this.foundItems.addAll(foundItems)
        notifyDataSetChanged()
    }
}
