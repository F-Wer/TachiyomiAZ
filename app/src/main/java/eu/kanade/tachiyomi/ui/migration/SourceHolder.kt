package eu.kanade.tachiyomi.ui.migration

import android.view.View
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.source.icon
import eu.kanade.tachiyomi.ui.base.holder.BaseFlexibleViewHolder
import eu.kanade.tachiyomi.ui.base.holder.SlicedHolder
import io.github.mthli.slice.Slice
import kotlinx.android.synthetic.main.source_main_controller_card_item.card
import kotlinx.android.synthetic.main.source_main_controller_card_item.image
import kotlinx.android.synthetic.main.source_main_controller_card_item.source_browse
import kotlinx.android.synthetic.main.source_main_controller_card_item.source_latest
import kotlinx.android.synthetic.main.source_main_controller_card_item.title

class SourceHolder(view: View, override val adapter: SourceAdapter) :
    BaseFlexibleViewHolder(view, adapter),
    SlicedHolder {

    override val slice = Slice(card).apply {
        setColor(adapter.cardBackground)
    }

    override val viewToSlice: View
        get() = card

    init {
        source_latest.text = "Auto"
        source_browse.setText(R.string.select)
        source_browse.setOnClickListener {
            adapter.selectClickListener?.onSelectClick(bindingAdapterPosition)
        }
        source_latest.setOnClickListener {
            adapter.autoClickListener?.onAutoClick(bindingAdapterPosition)
        }
    }

    fun bind(item: SourceItem) {
        val source = item.source
        setCardEdges(item)

        // Set source name
        title.text = source.name

        // Set source icon
        itemView.post {
            image.setImageDrawable(source.icon())
        }
    }
}
