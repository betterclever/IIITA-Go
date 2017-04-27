package `in`.ac.iiita.go

import `in`.ac.iiita.go.api.GoService
import `in`.ac.iiita.go.fragments.LectureFragment
import `in`.ac.iiita.go.fragments.LibraryFragment
import `in`.ac.iiita.go.models.Lecture
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        addDummyDataToRealm()
        supportFragmentManager.beginTransaction().add(R.id.homeFrame,LectureFragment()).commit()

        GoService().storeData(this)
    }

    fun addDummyDataToRealm(){
        Realm.init(this)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()

        realm.copyToRealmOrUpdate(Lecture("SMAT432Cs",1230,3230,"SMAT430C","Monday","CC3","Theory", null))
        realm.copyToRealmOrUpdate(Lecture("SMAT432Cs1",1230,3230,"SMAT430C","Monday","CC3","Theory", null))
        realm.copyToRealmOrUpdate(Lecture("SMAT432Cs2",1230,3230,"SMAT430C","Monday","CC3","Theory", null))
        realm.copyToRealmOrUpdate(Lecture("SMAT432Cs3",1230,3230,"SMAT430C","Monday","CC3","Theory", null))

        realm.commitTransaction()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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

        if (id == R.id.nav_timetable) {
            startActivity<BookAddActivity>();

        } else if (id == R.id.nav_messMenu) {
            supportFragmentManager.beginTransaction().replace(R.id.homeFrame,LibraryFragment()).commit()

        } else if (id == R.id.nav_library) {

        } else if (id == R.id.nav_lostFound) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START)

        return true
    }
}
