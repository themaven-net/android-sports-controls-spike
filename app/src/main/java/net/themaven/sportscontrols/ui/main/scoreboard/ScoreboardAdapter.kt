package net.themaven.sportscontrols.ui.main.scoreboard

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_scoreboard_item.view.*
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

class ScoreboardAdapter() : RecyclerViewSectionedAdapter() {

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
            R.layout.header_scoreboard, parent, false)
        return ScoreboardHeaderViewHolder(view)
    }

    override fun createCellViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_scoreboard_item, parent, false)
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
                scoreboard_header_title.text = dayDate
            }
        }

    }

    class ScoreboardItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(fixture : Fixture) {
            with(itemView) {

                var homeTeam = fixture.homeTeam
                var awayTeam = fixture.awayTeam

                if (homeTeam.isWinner || awayTeam.isWinner) {
                    scoreboard_final_text.visibility = View.VISIBLE
                } else {
                    scoreboard_final_text.visibility = View.GONE
                }

                scoreboard_team_1_score.setTypeface(Typeface.DEFAULT);
                scoreboard_team_1_name.setTypeface(Typeface.DEFAULT);
                scoreboard_team_2_score.setTypeface(Typeface.DEFAULT);
                scoreboard_team_2_name.setTypeface(Typeface.DEFAULT);

                if (homeTeam.isWinner) {
                    scoreboard_team_1_score.setTypeface(Typeface.DEFAULT_BOLD);
                    scoreboard_team_1_name.setTypeface(Typeface.DEFAULT_BOLD);
                }
                if (awayTeam.isWinner) {
                    scoreboard_team_2_score.setTypeface(Typeface.DEFAULT_BOLD);
                    scoreboard_team_2_name.setTypeface(Typeface.DEFAULT_BOLD);
                }

                scoreboard_team_1_location.text = homeTeam.location.name
                scoreboard_team_1_name.text = homeTeam.name
                scoreboard_team_1_score.text = homeTeam.score.toString()
                scoreboard_team_1_record.text = homeTeam.wins.toString() +
                        "-" + homeTeam.losses.toString()
                scoreboard_team_1_logo.setImageDrawable(null)
                DownloadImageTask(scoreboard_team_1_logo).execute(homeTeam.defaultImageURL)

                scoreboard_team_2_location.text = awayTeam.location.name
                scoreboard_team_2_name.text = awayTeam.name
                scoreboard_team_2_score.text = awayTeam.score.toString()
                scoreboard_team_2_record.text = awayTeam.wins.toString() +
                        "-" + awayTeam.losses.toString()
                scoreboard_team_2_logo.setImageDrawable(null)
                DownloadImageTask(scoreboard_team_2_logo).execute(awayTeam.defaultImageURL)

            }
        }

    }

}