package `in`.ac.iiita.go.fragments


import `in`.ac.iiita.go.R
import `in`.ac.iiita.go.adapter.FoundItemAdapter
import `in`.ac.iiita.go.api.GoService
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_found.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class FoundFragment : Fragment() {

    var goService : GoService? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        goService = GoService(context)
        return inflater!!.inflate(R.layout.fragment_found, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FoundItemAdapter()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        doAsync {

            val list = goService!!.iiitaGoApi.getAllFoundItems().execute().body()
            uiThread {
                adapter.addItems(list)
            }
        }
    }
}