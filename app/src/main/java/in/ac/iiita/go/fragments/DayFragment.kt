package `in`.ac.iiita.go.fragments


import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.adapter.LectureAdapter
import `in`.ac.iiita.go.adapter.MessAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_day.*
import org.jetbrains.anko.AnkoLogger

/**
 * A simple [Fragment] subclass.
 */
class LectureDayFragment : Fragment(), AnkoLogger {

    var dayNum = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_day, container, false)
        dayNum = arguments.getInt("dayNum")
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = LectureAdapter(context,dayNum)
    }

}


class MessDayFragment : Fragment(), AnkoLogger {

    var dayNum = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_day, container, false)
        dayNum = arguments.getInt("dayNum")
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MessAdapter(context,dayNum)
    }

}
