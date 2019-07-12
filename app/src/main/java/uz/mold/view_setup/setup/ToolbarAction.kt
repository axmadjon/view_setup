package uz.mold.view_setup.setup

import android.view.View

/**
 * ToolbarAction toolbar menu item
 *
 * @param title is menu title
 * @param iconResId is menu icon
 * @param command is menu click action
 * @param submenu is when submenu true else false
 * @param view is toolbar menu view
 * */
class ToolbarAction(
    val title: Any,
    val iconResId: Int = -1,
    val command: (() -> Unit)?,
    val submenu: Boolean = false,
    val view: View? = null
) {

    var id = 0
}
