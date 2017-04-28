package `in`.ac.iiita.go.fragments


import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.adapter.MessViewPagerAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mess.*

class MessFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_mess, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter  = MessViewPagerAdapter(activity.supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        (activity as AppCompatActivity).supportActionBar!!.title = "Mess Schedule"
    }
}
