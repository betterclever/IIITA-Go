package `in`.ac.iiita.go.activities

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.LostItem
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.content_report_lost_item.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.text.SimpleDateFormat
import java.util.*

class ReportLostItemActivity : AppCompatActivity(),AnkoLogger {
    val calendar = Calendar.getInstance()
    val time = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_lost_item)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar[java.util.Calendar.YEAR] = year
            calendar[java.util.Calendar.MONTH] = month
            calendar[java.util.Calendar.DAY_OF_MONTH] = dayOfMonth
            updateLabel()
        }

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            time[Calendar.HOUR] = hour
            time[Calendar.MINUTE] = minute
            updateTime()
            info(hour)
            info(minute)
        }

        lostDateET.setOnClickListener {
            DatePickerDialog(this, dateSetListener, calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]).show()
        }
        lostTimeET.setOnClickListener {
            TimePickerDialog(this, timeSetListener, time[Calendar.HOUR],
                    time[Calendar.MINUTE],false).show()
        }

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            val report =  LostItem(
                    id = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date()),
                    name = itemNameET.text.toString(),
                    description = itemDescriptionET.text.toString(),
                    ownerName = ownerNameTV.text.toString(),
                    extraDetail = lostDateET.text.toString()+ " " + lostTimeET.text.toString(),
                    ownerPhone = notesET.text.toString().toLong())
            info(report)
        }


    }
    fun updateLabel() {
        val format = "MM/dd/yyyy"
        val sdf = java.text.SimpleDateFormat(format, Locale.US)
        lostDateET.setText(sdf.format(calendar.time))
    }
    fun updateTime(){
        val format = "hh:mm a"
        val sdf = SimpleDateFormat(format, Locale.US)
        lostTimeET.setText(sdf.format(time.time))
    }
}
