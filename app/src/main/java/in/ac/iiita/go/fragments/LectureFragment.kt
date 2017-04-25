package `in`.ac.iiita.go.fragments


import `in`.ac.iiita.go.DayViewPagerAdapter
import `in`.ac.iiita.go.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_lecture.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class LectureFragment : Fragment(), AnkoLogger {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        info("Here")
        return inflater!!.inflate(R.layout.fragment_lecture, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = DayViewPagerAdapter(activity.supportFragmentManager)
    }
}
