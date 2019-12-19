package net.themaven.sportscontrols.ui.main.schedule

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_schedule_item.view.*
import kotlinx.android.synthetic.main.card_scoreboard_item.view.*
import kotlinx.android.synthetic.main.header_schedule.view.*
import kotlinx.android.synthetic.main.header_scoreboard.view.*
import net.themaven.sportscontrols.R
import net.themaven.sportscontrols.model.Fixture
import net.themaven.sportscontrols.model.StatsApi
import net.themaven.sportscontrols.model.TimeQuantum
import net.themaven.sportscontrols.ui.main.customClasses.DownloadImageTask
import net.themaven.sportscontrols.ui.main.customClasses.IndexPath
import net.themaven.sportscontrols.ui.main.customClasses.RecyclerViewSectionedAdapter
import java.text.SimpleDateFormat
import java.util.*

class ScheduleAdapter() : RecyclerViewSectionedAdapter() {

    private var currentQuantum : TimeQuantum? = null

    fun setNewQuantum(newQuantum: TimeQuantum) {
        this.currentQuantum = newQuantum
        notifyDataSetChanged()
    }

    override fun numberOfSections() : Int {
        return when {
            currentQuantum != null -> currentQuantum!!.dateElements.size
            else -> 0
        }
    }

    override fun numberOfRows(inSection: Int) : Int {
        return when {
            currentQuantum != null -> currentQuantum!!.dateElements.get(inSection).fixtures.size
            else -> 0
        }
    }

    override fun createHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.header_schedule, parent, false)
        return ScoreboardHeaderViewHolder(view)
    }

    override fun createCellViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_schedule_item, parent, false)
        return ScoreboardItemViewHolder(view)
    }

    override fun bindHeader(forSection: Int, viewHolder: RecyclerView.ViewHolder) {
        when (viewHolder) {
            is ScoreboardHeaderViewHolder -> viewHolder.bind(currentQuantum!!.dateElements.get(forSection))
        }
    }

    override fun bindCell(forIndexPath: IndexPath, viewHolder: RecyclerView.ViewHolder) {

        val fixture = currentQuantum!!.dateElements.get(forIndexPath.section).fixtures.get(forIndexPath.row)
        when (viewHolder) {
            is ScoreboardItemViewHolder -> viewHolder.bind(fixture)
        }
    }

    class ScoreboardHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(dateElement : StatsApi.DateElement) {
            with(itemView) {

                val start = GregorianCalendar(dateElement.date.year, dateElement.date.month - 1, dateElement.date.day).time
                val simpleDateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy")
                val dayDate = simpleDateFormat.format(start)
                schedule_header_title.text = dayDate
            }
        }

    }

    class ScoreboardItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(fixture : Fixture) {
            with(itemView) {

                var homeTeam = fixture.homeTeam
                var awayTeam = fixture.awayTeam

                schedule_card_home_team_name.text = awayTeam.abbreviation.toUpperCase()
                schedule_card_away_team_name.text = homeTeam.abbreviation.toUpperCase()
                schedule_card_time.text = fixture.start.utc

                DownloadImageTask(schedule_card_home_team_logo).execute(homeTeam.defaultImageURL)
                DownloadImageTask(schedule_card_away_team_logo).execute(awayTeam.defaultImageURL)

            }
        }

    }

}