package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.LibraryBook
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.layout_library.view.*
import java.text.SimpleDateFormat

/**
 * Created by betterclever on 4/26/2017.
 */

class BookAdapter(val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var libraryBooks: RealmResults<LibraryBook>

    init {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        libraryBooks = realm.where(LibraryBook::class.java).findAll()
    }


    override fun getItemCount() = libraryBooks.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindMessItem(libraryBooks[position],context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_library, parent, false))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindMessItem(libraryBook: LibraryBook, context: Context){

            val sdf = SimpleDateFormat()
            sdf.applyPattern("dd/MM/YYYY")

            itemView.bookNameTV.text = libraryBook.name
            itemView.returnDateTV.text = "Return Date: "+ sdf.format(libraryBook.issueDate)
            itemView.notificationSwitch.isChecked = libraryBook.reminderEnabled
        }
    }
}