package `in`.ac.iiita.go

import `in`.ac.iiita.go.models.LibraryBook
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_book_add.*
import kotlinx.android.synthetic.main.content_book_add.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class BookAddActivity : AppCompatActivity(), AnkoLogger {

    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_add)
        setSupportActionBar(toolbar)

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            updateLabel()
        }

        Realm.init(this)
        val realm = Realm.getDefaultInstance()

        returnDateEditText.setOnClickListener {
            DatePickerDialog(this,dateSetListener, calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]).show()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()


            info {
                returnDateEditText.editableText.toString()
            }

            val book = LibraryBook(accNoEditText.editableText.toString(),
                    bookNameEditText.editableText.toString(), calendar.time,
                    notesET.editableText.toString(), reminderSwitch.isEnabled)

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(book)
            realm.commitTransaction()

            toast("Book Added Successfully")
            finish()
        }

    }

    fun updateLabel() {
        val format = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
        returnDateEditText.setText(sdf.format(calendar.time))
    }
}
