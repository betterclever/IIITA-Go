package `in`.ac.iiita.go.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList

import `in`.ac.iiita.go.HomeActivity
import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.api.LoginAPI
import android.os.AsyncTask.execute
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Response
import retrofit2.Retrofit
import okhttp3.ResponseBody
import retrofit2.Call
import android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.authenticate




class Login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val spinnerArraysection = ArrayList<String>()
        spinnerArraysection.add("A")
        spinnerArraysection.add("B")
        spinnerArraysection.add("C")

        val spinnerArraycourse = ArrayList<String>()
        spinnerArraycourse.add("B.Tech")
        spinnerArraycourse.add("M.Tech")

        val spinnerArraysemester = ArrayList<String>()
        spinnerArraysemester.add("1")
        spinnerArraysemester.add("2")
        spinnerArraysemester.add("3")
        spinnerArraysemester.add("4")
        spinnerArraysemester.add("5")
        spinnerArraysemester.add("6")
        spinnerArraysemester.add("7")
        spinnerArraysemester.add("8")

        val adapterSection = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, spinnerArraysection)
        val adapterCourse = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, spinnerArraycourse)
        val adapterSemester = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, spinnerArraysemester)

        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        var sItemsSec: Spinner
        var sItemsCourse: Spinner
        var sItemsSem: Spinner

        sItemsSec = findViewById(R.id.sec) as Spinner
        sItemsSec.adapter = adapterSection

        sItemsCourse = findViewById(R.id.course) as Spinner
        sItemsCourse.adapter = adapterCourse

        sItemsSem = findViewById(R.id.sem) as Spinner
        sItemsSem.adapter = adapterSemester

    }

    fun save(view: View) {
        val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl("http://172.26.46.140")
                .build()
        val api_auth:LoginAPI = retrofit.create(LoginAPI::class.java)
        val response = api_auth.checkAuth(enr.text.toString() , pass.text.toString()).execute()

        if("1".equals(response.body())) {
            var editor: SharedPreferences.Editor
            var sharedpreferences: SharedPreferences
            sharedpreferences = getSharedPreferences("INFO_USR", Context.MODE_PRIVATE)
            editor = sharedpreferences.edit()
            editor.putString("NAME", name.text.toString() + "")
            editor.putString("ROLL", enr.toString() + "")
            editor.putString("SEC", sec.selectedItem.toString() + "")
            editor.putString("COURSE", course.selectedItem.toString() + "")
            editor.putString("SEMESTER", sem.selectedItem.toString())
            editor.commit()
            Toast.makeText(this@Login, "Thanks..", Toast.LENGTH_LONG).show()
            val myIntent = Intent(this@Login, HomeActivity::class.java)
            this@Login.startActivity(myIntent)
        }else{
            Toast.makeText(this@Login, "Wrong LDAP credentials..", Toast.LENGTH_LONG).show()
        }
    }
}
