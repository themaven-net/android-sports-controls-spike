package net.themaven.sportscontrols.ui.main.customClasses

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger

data class IndexPath(var section: Int, var row: Int)

abstract class RecyclerViewSectionedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun numberOfSections() : Int
    abstract fun numberOfRows(inSection: Int) : Int
    abstract fun createHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
    abstract fun createCellViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
    abstract fun bindHeader(forSection: Int, viewHolder: RecyclerView.ViewHolder)
    abstract fun bindCell(forIndexPath: IndexPath, viewHolder: RecyclerView.ViewHolder)

    override fun getItemCount(): Int {
        var count = numberOfSections()
        for(n in 0 until numberOfSections()){
            count += numberOfRows(n)
        }
        return count
    }

    override fun getItemViewType(position: Int): Int {

        val indexPath = convertPositionToIndexPath(position)
        return if (indexPath.row == SECTION_HEADER_INDEX) {
            VIEW_TYPE_HEADER
        }  else {
            VIEW_TYPE_CELL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            VIEW_TYPE_HEADER -> { createHeaderViewHolder(parent) }
            else -> { createCellViewHolder(parent) }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val indexPath = convertPositionToIndexPath(position)
        when (holder.itemViewType) {
            VIEW_TYPE_HEADER -> bindHeader(indexPath.section, holder)
            else -> bindCell(indexPath, holder)
        }
    }

    internal fun convertPositionToIndexPath(position : Int) : IndexPath {

        var currentSectionHeaderRowIndex = -1

        for (currentSection in 0 until numberOfSections()) {

            currentSectionHeaderRowIndex++

            if (currentSectionHeaderRowIndex == position) {
                return IndexPath(currentSection, SECTION_HEADER_INDEX)
            }

            val channelCount = numberOfRows(currentSection)

            if (channelCount + currentSectionHeaderRowIndex >= position) {
                return IndexPath(currentSection, position - currentSectionHeaderRowIndex - 1)
            } else {
                currentSectionHeaderRowIndex += channelCount
            }
        }

        Logger.e("Error calculating IndexPath. HELP!")
        return IndexPath(0,0)
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_CELL = 1
        private const val SECTION_HEADER_INDEX = -1
    }
}