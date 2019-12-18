package net.themaven.sportscontrols.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import net.themaven.sportscontrols.dataHelpers.SportsScheduleBuilder
import net.themaven.sportscontrols.ui.main.scoreboard.ScoreboardFragment

private val TAB_TITLES = arrayOf(
    "NBA Scoreboard",
    "NHL Scoreboard",
    "NFL Scoreboard",
    "Placeholder",
    "Placeholder",
    "Placeholder"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        val positionFragment = when (position) {
            0 -> ScoreboardFragment.newInstance(SportsScheduleBuilder.SportType.NBA)
            1 -> ScoreboardFragment.newInstance(SportsScheduleBuilder.SportType.NHL)
            2 -> ScoreboardFragment.newInstance(SportsScheduleBuilder.SportType.NFL)
            else -> PlaceholderFragment.newInstance(position + 1)
        }

        return positionFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 6
    }
}