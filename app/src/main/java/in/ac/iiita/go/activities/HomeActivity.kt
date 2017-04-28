package `in`.ac.iiita.go.activities

import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.api.GoService
import `in`.ac.iiita.go.fragments.LectureFragment
import `in`.ac.iiita.go.fragments.LibraryFragment
import `in`.ac.iiita.go.fragments.MessFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

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

        supportFragmentManager.beginTransaction().add(R.id.homeFrame, `in`.ac.iiita.go.fragments.LectureFragment()).commit()

        val go = GoService(this)
        go.storeData()

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
