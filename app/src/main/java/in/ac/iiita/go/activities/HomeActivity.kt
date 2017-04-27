package `in`.ac.iiita.go.activities

import `in`.ac.iiita.go.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : android.support.v7.app.AppCompatActivity(), android.support.design.widget.NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(`in`.ac.iiita.go.R.layout.activity_home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            android.support.design.widget.Snackbar.make(view, "Replace with your own action", android.support.design.widget.Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = android.support.v7.app.ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(`in`.ac.iiita.go.R.id.nav_view) as android.support.design.widget.NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        addDummyDataToRealm()
        supportFragmentManager.beginTransaction().add(`in`.ac.iiita.go.R.id.homeFrame, `in`.ac.iiita.go.fragments.LectureFragment()).commit()

        `in`.ac.iiita.go.api.GoService().storeData(this)
    }

    fun addDummyDataToRealm(){
        io.realm.Realm.init(this)
        val realm = io.realm.Realm.getDefaultInstance()
        realm.beginTransaction()

        realm.copyToRealmOrUpdate(`in`.ac.iiita.go.models.Lecture("SMAT432Cs", 1230, 3230, "SMAT430C", "Monday", "CC3", "Theory", null))
        realm.copyToRealmOrUpdate(`in`.ac.iiita.go.models.Lecture("SMAT432Cs1", 1230, 3230, "SMAT430C", "Monday", "CC3", "Theory", null))
        realm.copyToRealmOrUpdate(`in`.ac.iiita.go.models.Lecture("SMAT432Cs2", 1230, 3230, "SMAT430C", "Monday", "CC3", "Theory", null))
        realm.copyToRealmOrUpdate(`in`.ac.iiita.go.models.Lecture("SMAT432Cs3", 1230, 3230, "SMAT430C", "Monday", "CC3", "Theory", null))

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
        menuInflater.inflate(`in`.ac.iiita.go.R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        val id = item.itemId


        if (id == `in`.ac.iiita.go.R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: android.view.MenuItem): Boolean {
        val id = item.itemId

        if (id == `in`.ac.iiita.go.R.id.nav_camera) {
            startActivity<BookAddActivity>()

        } else if (id == `in`.ac.iiita.go.R.id.nav_gallery) {
            supportFragmentManager.beginTransaction().replace(`in`.ac.iiita.go.R.id.homeFrame, `in`.ac.iiita.go.fragments.LibraryFragment()).commit()

        } else if (id == `in`.ac.iiita.go.R.id.nav_slideshow) {

        } else if (id == `in`.ac.iiita.go.R.id.nav_manage) {

        } else if (id == `in`.ac.iiita.go.R.id.nav_share) {

        } else if (id == `in`.ac.iiita.go.R.id.nav_send) {

        }

        drawer.closeDrawer(android.support.v4.view.GravityCompat.START)

        return true
    }
}
