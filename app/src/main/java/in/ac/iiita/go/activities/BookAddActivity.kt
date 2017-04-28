package `in`.ac.iiita.go.activities

import `in`.ac.iiita.go.R
import kotlinx.android.synthetic.main.activity_book_add.*
import kotlinx.android.synthetic.main.content_book_add.*
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import java.util.*

class BookAddActivity : android.support.v7.app.AppCompatActivity(), org.jetbrains.anko.AnkoLogger {

    val calendar = java.util.Calendar.getInstance()

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_add)
        setSupportActionBar(toolbar)

        val dateSetListener = android.app.DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar[java.util.Calendar.YEAR] = year
            calendar[java.util.Calendar.MONTH] = month
            calendar[java.util.Calendar.DAY_OF_MONTH] = dayOfMonth
            updateLabel()
        }

        io.realm.Realm.init(this)
        val realm = io.realm.Realm.getDefaultInstance()

        returnDateEditText.setOnClickListener {
            android.app.DatePickerDialog(this, dateSetListener, calendar[java.util.Calendar.YEAR],
                    calendar[java.util.Calendar.MONTH], calendar[java.util.Calendar.DAY_OF_MONTH]).show()
        }

        fab.setOnClickListener { view ->
            android.support.design.widget.Snackbar.make(view, "Replace with your own action", android.support.design.widget.Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()


            info {
                returnDateEditText.editableText.toString()
            }

            val book = `in`.ac.iiita.go.models.LibraryBook(accNoEditText.editableText.toString(),
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
        val sdf = java.text.SimpleDateFormat(format, Locale.US)
        returnDateEditText.setText(sdf.format(calendar.time))
    }
}
