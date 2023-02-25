package eu.kanade.tachiyomi.ui.library

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.viewholders.FlexibleViewHolder

/**
 * Generic class used to hold the displayed data of a manga in the library.
 * @param view the inflated view for this holder.
 * @param adapter the adapter handling this holder.
 * @param listener a listener to react to the single tap and long tap events.
 */

abstract class LibraryHolder(
    view: View,
    adapter: FlexibleAdapter<*>
) : FlexibleViewHolder(view, adapter) {

    /**
     * Method called from [LibraryCategoryAdapter.onBindViewHolder]. It updates the data for this
     * holder with the given manga.
     *
     * @param item the manga item to bind.
     */
    abstract fun onSetValues(item: LibraryItem)

    /**
     * Called when an item is released.
     *
     * @param position The position of the released item.
     */
    override fun onItemReleased(position: Int) {
        super.onItemReleased(position)
        (adapter as? LibraryCategoryAdapter)?.onItemReleaseListener?.onItemReleased(position)
    }
}
