package `in`.ac.iiita.go.adapter

import `in`.ac.iiita.go.fragments.LectureDayFragment
import `in`.ac.iiita.go.fragments.MessDayFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import org.jetbrains.anko.support.v4.withArguments

/**
 * Created by betterclever on 4/25/2017.
 */

class LectureViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int) = LectureDayFragment().withArguments("dayNum" to position)

    override fun getCount() = 6

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> "Mon"
            1 -> "Tue"
            2 -> "Wed"
            3 -> "Thu"
            4 -> "Fri"
            5 -> "Sat"
            else -> {
                "Mon"
            }
        }
    }
}

class MessViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int) = MessDayFragment().withArguments("dayNum" to position)

    override fun getCount() = 7

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> "Mon"
            1 -> "Tue"
            2 -> "Wed"
            3 -> "Thu"
            4 -> "Fri"
            5 -> "Sat"
            else -> {
                "Sun"
            }
        }
    }
}
