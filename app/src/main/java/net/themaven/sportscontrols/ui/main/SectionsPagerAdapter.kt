package net.themaven.sportscontrols.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import net.themaven.sportscontrols.dataHelpers.SportType
import net.themaven.sportscontrols.ui.main.schedule.ScheduleFragment
import net.themaven.sportscontrols.ui.main.scoreboard.ScoreboardFragment

private val TAB_TITLES = arrayOf(
    "NBA Scoreboard",
    "NBA Schedule",
    "NHL Scoreboard",
    "NHL Schedule",
    "NFL Scoreboard",
    "NFL Schedule"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    val cachedFragments = HashMap<Int, Fragment>()

    override fun getItem(position: Int): Fragment {

        if (cachedFragments[position] != null) {
            return cachedFragments[position]!!
        }

        val positionFragment = when (position) {
            0 -> ScoreboardFragment.newInstance(SportType.NBA)
            1 -> ScheduleFragment.newInstance(SportType.NBA)
            2 -> ScoreboardFragment.newInstance(SportType.NHL)
            3 -> ScheduleFragment.newInstance(SportType.NHL)
            4 -> ScoreboardFragment.newInstance(SportType.NFL)
            5 -> ScheduleFragment.newInstance(SportType.NFL)
            else -> PlaceholderFragment.newInstance(position + 1)
        }
        cachedFragments[position] = positionFragment
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