package `in`.ac.iiita.go.activities

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.api.LoginAPI
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val spinnerArraySection = ArrayList<String>()
        spinnerArraySection.add("A")
        spinnerArraySection.add("B")
        spinnerArraySection.add("C")

        val spinnerArrayCourse = ArrayList<String>()
        spinnerArrayCourse.add("B.Tech")
        spinnerArrayCourse.add("M.Tech")

        val spinnerArraySemester = ArrayList<String>()
        spinnerArraySemester.add("1")
        spinnerArraySemester.add("2")
        spinnerArraySemester.add("3")
        spinnerArraySemester.add("4")
        spinnerArraySemester.add("5")
        spinnerArraySemester.add("6")
        spinnerArraySemester.add("7")
        spinnerArraySemester.add("8")

        val spinnerArrayHostel = ArrayList<String>()
        spinnerArrayHostel.add("BH1");
        spinnerArrayHostel.add("BH2");
        spinnerArrayHostel.add("BH3");
        spinnerArrayHostel.add("BH4");
        spinnerArrayHostel.add("BH5");
        spinnerArrayHostel.add("GH1");
        spinnerArrayHostel.add("GH2");
        spinnerArrayHostel.add("GH3");

        val adapterSection = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, spinnerArraySection)
        val adapterCourse = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, spinnerArrayCourse)
        val adapterSemester = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, spinnerArraySemester)
        val adapterHostel = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, spinnerArrayHostel)

        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterHostel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        secSpinner.adapter = adapterSection
        courseSpinner.adapter = adapterCourse
        semSpinner.adapter = adapterSemester
        hostelSpinner.adapter = adapterHostel

        // For testing duration only
        startActivity<HomeActivity>()
    }

    fun save(view: View) {

        doAsync {

            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("http://172.26.46.140")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            val api_auth: LoginAPI = retrofit.create(LoginAPI::class.java)
            val response = api_auth.checkAuth(enr.text.toString() , pass.text.toString()).execute()

            if("1" == response.body()) {

                uiThread {

                    val sharedPreferences = getSharedPreferences("INFO_USR", Context.MODE_PRIVATE)

                    val editor = sharedPreferences.edit()
                    editor.putString("NAME", name.text.toString())
                    editor.putString("ROLL", enr.toString())
                    editor.putString("SEC", secSpinner.selectedItem.toString())
                    editor.putString("COURSE", courseSpinner.selectedItem.toString())
                    editor.putString("SEMESTER", semSpinner.selectedItem.toString())
                    editor.putString("HOSTEL",hostelSpinner.selectedItem.toString().toLowerCase())
                    editor.commit()

                    toast("Logged in Successfully!! ")
                    startActivity<HomeActivity>()
                }

            }else{

                uiThread {
                    toast("Wrong LDAP Credentials")
                }
            }
        }

    }
}
 