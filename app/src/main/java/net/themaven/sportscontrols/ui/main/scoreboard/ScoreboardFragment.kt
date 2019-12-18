package net.themaven.sportscontrols.ui.main.scoreboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_scoreboard.*

import net.themaven.sportscontrols.R
import net.themaven.sportscontrols.dataHelpers.ResponseInterface
import net.themaven.sportscontrols.dataHelpers.SportsScheduleBuilder
import net.themaven.sportscontrols.model.SportsScheduleViewModel
import net.themaven.sportscontrols.model.TimeQuantum

class ScoreboardFragment(val sportType : SportsScheduleBuilder.SportType) : Fragment(), ResponseInterface {


    var scoreboardAdapter : ScoreboardAdapter? = null
    var sportsScheduleViewModel = SportsScheduleViewModel(sportType)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scoreboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //get all the sorted quanta and initialize the tabs
        val sortedQuanta = sportsScheduleViewModel.timeSortedQuanta()
        for (quanta in sortedQuanta) {
            scoreboard_tabs.addTab(scoreboard_tabs.newTab().setText(quanta.title))
        }

        //add listener to fetch data when new quantum tab clicked
        scoreboard_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabKey = tab?.text.toString()
                fetchNewQuantum(tabKey)
            }
        })

        //initialize first quanta
        if (sortedQuanta.size > 0) {
            fetchNewQuantum(sortedQuanta.get(0).title)
        }

        //set up recyclerView
        val linearLayoutManager = LinearLayoutManager(activity)
        scoreboard_recyclerView.layoutManager = linearLayoutManager
        scoreboardAdapter = ScoreboardAdapter()
        //now adding the adapter to recyclerview
        scoreboard_recyclerView.adapter = scoreboardAdapter

    }

    fun fetchNewQuantum(forKey : String?) {
        forKey?.let {
            sportsScheduleViewModel.quantumAsync(it, this)
        }
    }

    override fun success(timeQuantum : TimeQuantum) {
        scoreboardAdapter?.setNewQuantum(timeQuantum)
    }

    override fun failure(error : String) {
        //TODO: log error
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(sportType : SportsScheduleBuilder.SportType) =
            ScoreboardFragment(sportType).apply {
                arguments = Bundle().apply {
                }
            }
    }
}
