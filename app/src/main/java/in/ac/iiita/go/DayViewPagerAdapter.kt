package `in`.ac.iiita.go

import `in`.ac.iiita.go.fragments.DayFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import org.jetbrains.anko.support.v4.withArguments

/**
 * Created by betterclever on 4/25/2017.
 */

class DayViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int) = DayFragment().withArguments("dayNum" to position)

    override fun getCount() = 6

}
