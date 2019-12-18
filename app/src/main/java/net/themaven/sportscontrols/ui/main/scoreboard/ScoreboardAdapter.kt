package net.themaven.sportscontrols.ui.main.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_scoreboard_item.view.*
import net.themaven.sportscontrols.R
import net.themaven.sportscontrols.model.Fixture
import net.themaven.sportscontrols.model.TimeQuantum

class ScoreboardAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var currentQuantum : TimeQuantum? = null
    private var fixtures : ArrayList<Fixture>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_scoreboard_item, parent, false)
        return ScoreboardItemViewHolder(view)
    }

    fun setNewQuantum(newQuantum: TimeQuantum) {
        currentQuantum = newQuantum
        fixtures = newQuantum.fixtures()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ScoreboardItemViewHolder -> holder.bind(fixtures!!.get(position))
        }
    }

    override fun getItemCount() : Int {
        return when {
            fixtures != null -> fixtures!!.count()
            else -> 0
        }
    }

    class ScoreboardItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(fixture : Fixture) {
            with(itemView) {

                var text = ""
                for (team in fixture.teams) {
                    text += team.name + " "
                }

                scoreboard_fixture_title.text = text
            }
        }

    }

}