package `in`.ac.iiita.go.activities

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.api.GoService
import `in`.ac.iiita.go.fragments.LectureFragment
import `in`.ac.iiita.go.fragments.LibraryFragment
import `in`.ac.iiita.go.fragments.MessFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        FirebaseApp.initializeApp(this)

        FirebaseMessaging.getInstance().subscribeToTopic("everything")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)


        val nameTV = navigationView.getHeaderView(0).findViewById(R.id.nameTV) as TextView
        nameTV.text = "Pranjal Paliwal"

        supportFragmentManager.beginTransaction().add(R.id.homeFrame, LectureFragment()).commit()

        val go = GoService(this)
        go.storeData()

    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id){
            R.id.nav_timetable ->
                supportFragmentManager.beginTransaction().replace(R.id.homeFrame,LectureFragment()).commit()

            R.id.nav_messMenu ->
                supportFragmentManager.beginTransaction().replace(R.id.homeFrame,MessFragment()).commit()

            R.id.nav_library ->
                supportFragmentManager.beginTransaction().replace(R.id.homeFrame,LibraryFragment()).commit()
        }

        drawer.closeDrawer(android.support.v4.view.GravityCompat.START)

        return true
    }
}
