package `in`.ac.iiita.go.fragments


import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.activities.BookAddActivity
import `in`.ac.iiita.go.adapter.BookAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_library.*
import org.jetbrains.anko.support.v4.startActivity


class LibraryFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BookAdapter(context)

        fab.setOnClickListener {
            startActivity<BookAddActivity>()
        }

        (activity as AppCompatActivity).supportActionBar!!.title = "Issued Books"
    }
}