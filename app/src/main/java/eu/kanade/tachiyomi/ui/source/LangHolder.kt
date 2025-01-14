package eu.kanade.tachiyomi.ui.source

import android.view.View
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.viewholders.FlexibleViewHolder
import eu.kanade.tachiyomi.databinding.SourceMainControllerCardHeaderBinding
import eu.kanade.tachiyomi.util.system.LocaleHelper

class LangHolder(view: View, adapter: FlexibleAdapter<*>) :
    FlexibleViewHolder(view, adapter) {

    private val binding = SourceMainControllerCardHeaderBinding.bind(view)

    fun bind(item: LangItem) {
        binding.title.text = LocaleHelper.getSourceDisplayName(item.code, itemView.context)
    }
}
