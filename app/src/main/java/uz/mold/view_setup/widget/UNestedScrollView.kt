package uz.mold.view_setup.widget

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.View
import uz.mold.view_setup.VS

class UNestedScrollView : NestedScrollView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): UNestedScrollView {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * add child view in layout
     *
     * @param childs is var arrays View's
     *
     * @return this
     */
    fun addChild(vararg childs: View): UNestedScrollView {
        for (x in childs) super.addView(x)
        return this
    }


    companion object {

        fun create(
            content: Any,
            id: Int = -1,
            layoutParams: CoordinatorLayout.LayoutParams? = null
        ): UNestedScrollView {

            val view = UNestedScrollView(context = VS.castToContext(content))
                .setViewId(id)

            val finalLayoutParams = layoutParams ?: CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.MATCH_PARENT
            )

            if (layoutParams == null) {
                finalLayoutParams.behavior = AppBarLayout.ScrollingViewBehavior()
            }

            view.layoutParams = finalLayoutParams

            return view
        }
    }


}
