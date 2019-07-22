package uz.mold.view_setup.variable

import android.support.annotation.DrawableRes
import android.view.View

data class UTab(
    val text: Any? = null,
    val contentDescription: Any? = null,
    @DrawableRes val icon: Int? = null,
    val customView: View? = null,
    val tag: Any? = null
)