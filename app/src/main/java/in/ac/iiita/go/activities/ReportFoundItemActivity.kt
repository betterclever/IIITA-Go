package `in`.ac.iiita.go.activities

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.models.FoundItem
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.content_report_found_item.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.text.SimpleDateFormat
import java.util.*


class ReportFoundItemActivity : AppCompatActivity(), AnkoLogger {
    val calendar = Calendar.getInstance()
    val time = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_found_item)
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

        foundDateET.setOnClickListener {
            DatePickerDialog(this, dateSetListener, calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]).show()
        }

        foundTimeET.setOnClickListener {
            TimePickerDialog(this, timeSetListener, time[Calendar.HOUR],
                    time[Calendar.MINUTE],false).show()
        }
        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            val report =  FoundItem(
                    id = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date()),
                    name = itemNameET.text.toString(),
                    description = itemDescriptionET.text.toString(),
                    founderName = founderNameTV.text.toString(),
                    extraDetail = foundDateET.text.toString()+" " + foundTimeET.text.toString(),
                    founderPhone = notesET.text.toString().toLong())

            info(report)
        }
    }
    fun updateLabel() {
        val format = "MM/dd/yyyy"
        val sdf = java.text.SimpleDateFormat(format, Locale.US)
        foundDateET.setText(sdf.format(calendar.time))
    }
    fun updateTime(){
        val format = "hh:mm a"
        val sdf = SimpleDateFormat(format, Locale.US)
        foundTimeET.setText(sdf.format(time.time))
    }
}
