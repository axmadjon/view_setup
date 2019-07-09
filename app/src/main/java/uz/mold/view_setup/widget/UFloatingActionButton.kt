package uz.mold.view_setup.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.Gravity
import uz.mold.view_setup.R
import uz.mold.view_setup.VS
import uz.mold.view_setup.VSUtil

class UFloatingActionButton : FloatingActionButton {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): UFloatingActionButton {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    companion object {

        fun LayoutParams(ctx: Context): CoordinatorLayout.LayoutParams {
            val finalLayoutParams = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.WRAP_CONTENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
            val padding = VSUtil.dpToPx(ctx, 16)
            finalLayoutParams.gravity = Gravity.BOTTOM or Gravity.END
            finalLayoutParams.rightMargin = padding
            finalLayoutParams.bottomMargin = padding
            return finalLayoutParams
        }

        fun create(
            content: Any,
            id: Int = -1,
            layoutParams: CoordinatorLayout.LayoutParams? = null,
            icon: Any = R.drawable.ic_add_black_24dp,
            command: (() -> Unit)? = null
        ): UFloatingActionButton {
            val view = UFloatingActionButton(context = VS.castToContext(content))
                .setViewId(id)

            val finalLayoutParams = layoutParams ?: LayoutParams(ctx = VS.castToContext(content))
            view.layoutParams = finalLayoutParams

            if (icon is Int) {
                view.setImageResource(icon)

            } else if (icon is Drawable) {
                view.setImageDrawable(icon)
            }

            view.setOnClickListener { command?.invoke() }
            return view
        }
    }
}
