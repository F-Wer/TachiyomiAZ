@file:Suppress("NOTHING_TO_INLINE")

package eu.kanade.tachiyomi.util.view

import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.snackbar.Snackbar
import com.kennyc.textdrawable.ColorGenerator
import com.kennyc.textdrawable.TextDrawable
import eu.kanade.tachiyomi.R
import kotlin.math.min

/**
 * Returns coordinates of view.
 * Used for animation
 *
 * @return coordinates of view
 */
fun View.getCoordinates() = Point((left + right) / 2, (top + bottom) / 2)

/**
 * Shows a snackbar in this view.
 *
 * @param message the message to show.
 * @param length the duration of the snack.
 * @param f a function to execute in the snack, allowing for example to define a custom action.
 */
inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit): Snackbar {
    val snack = Snackbar.make(this, message, length)
    val textView: TextView = snack.view.findViewById(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snack.f()
    snack.show()
    return snack
}

/**
 * Shows a popup menu on top of this view.
 *
 * @param menuRes menu items to inflate the menu with.
 * @param initMenu function to execute when the menu after is inflated.
 * @param onMenuItemClick function to execute when a menu item is clicked.
 */
fun View.popupMenu(@MenuRes menuRes: Int, initMenu: (Menu.() -> Unit)? = null, onMenuItemClick: MenuItem.() -> Boolean) {
    val popup = PopupMenu(context, this, Gravity.NO_GRAVITY, R.attr.actionOverflowMenuStyle, 0)
    popup.menuInflater.inflate(menuRes, popup.menu)

    if (initMenu != null) {
        popup.menu.initMenu()
    }
    popup.setOnMenuItemClickListener { it.onMenuItemClick() }

    popup.show()
}

inline fun View.visible() {
    visibility = View.VISIBLE
}

inline fun View.invisible() {
    visibility = View.INVISIBLE
}

inline fun View.gone() {
    visibility = View.GONE
}

inline fun View.visibleIf(block: () -> Boolean) {
    visibility = if (block()) View.VISIBLE else View.GONE
}

/**
 * Sets a round TextDrawable into an ImageView determined by input.
 *
 * @param text text of [TextDrawable]
 */
fun ImageView.roundTextIcon(text: String) {
    val letter = text.take(1).toUpperCase()
    val size = min(this.width, this.height)

    TextDrawable(
            shape = TextDrawable.DRAWABLE_SHAPE_OVAL,
            desiredWidth = size,
            desiredHeight = size,
            typeFace = Typeface.DEFAULT,
            textColor = Color.WHITE,
            text = letter,
            color = ColorGenerator.MATERIAL.getColor(letter)
    )
}
