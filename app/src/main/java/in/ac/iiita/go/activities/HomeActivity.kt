package `in`.ac.iiita.go.activities

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.api.GoService
import `in`.ac.iiita.go.fragments.LibraryFragment
import `in`.ac.iiita.go.models.Lecture
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : android.support.v7.app.AppCompatActivity(), android.support.design.widget.NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            android.support.design.widget.Snackbar.make(view, "Replace with your own action", android.support.design.widget.Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = android.support.v7.app.ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as android.support.design.widget.NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        addDummyDataToRealm()
        supportFragmentManager.beginTransaction().add(R.id.homeFrame, `in`.ac.iiita.go.fragments.LectureFragment()).commit()

        val go = GoService(this)
        go.storeData()

    }

    fun addDummyDataToRealm(){
        io.realm.Realm.init(this)
        val realm = io.realm.Realm.getDefaultInstance()
        realm.beginTransaction()

        realm.copyToRealmOrUpdate(Lecture("SMAT432Cs", 1230, 3230,"A", "SMAT430C", "Monday", "CC3", "Theory", null))
        realm.copyToRealmOrUpdate(Lecture("SMAT432Cs1", 1230, 3230,"A", "SMAT430C", "Monday", "CC3", "Theory", null))
        realm.copyToRealmOrUpdate(Lecture("SMAT432Cs2", 1230, 3230,"A", "SMAT430C", "Monday", "CC3", "Theory", null))
        realm.copyToRealmOrUpdate(Lecture("SMAT432Cs3", 1230, 3230,"A", "SMAT430C", "Monday", "CC3", "Theory", null))

        realm.commitTransaction()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(android.support.v4.view.GravityCompat.START)) {
            drawer.closeDrawer(android.support.v4.view.GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: android.view.MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_timetable) {
            startActivity<BookAddActivity>()

        } else if (id == R.id.nav_messMenu) {
            supportFragmentManager.beginTransaction().replace(R.id.homeFrame, LibraryFragment()).commit()

        } else if (id == R.id.nav_library) {

        } else if (id == R.id.nav_lostFound) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(android.support.v4.view.GravityCompat.START)

        return true
    }
}
