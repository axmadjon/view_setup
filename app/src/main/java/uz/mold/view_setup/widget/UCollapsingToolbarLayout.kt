@file:Suppress("LocalVariableName")

package uz.mold.view_setup.widget

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import uz.mold.view_setup.R
import uz.mold.view_setup.VS
import uz.mold.view_setup.VSUtil

class UCollapsingToolbarLayout : CollapsingToolbarLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): UCollapsingToolbarLayout {
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
    fun addChild(vararg childs: View): UCollapsingToolbarLayout {
        for (x in childs) super.addView(x)
        return this
    }

    companion object {

        /**
         * create material CollapsingToolbarLayout
         *
         * @param content is Context, Fragment, or Activity
         * @param layoutParams is AppBarLayout.LayoutParams default @AppBarLayout.LayoutParams
         *                     with flag = scroll and exit until collapsed
         *
         * @return UCollapsingToolbarLayout
         */
        fun create(
            content: Any,
            id: Int = -1,
            layoutParams: AppBarLayout.LayoutParams? = null
        ): UCollapsingToolbarLayout {

            val view = UCollapsingToolbarLayout(context = VS.castToContext(content))
                .setViewId(id)

            val finalLayoutParams = layoutParams ?: UAppBarLayout.LayoutParams()
            if (layoutParams == null) {
                val scroll_1 = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                val scroll_2 = AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                finalLayoutParams.scrollFlags = scroll_1 or scroll_2
            }
            view.layoutParams = finalLayoutParams

            return view
        }

        /**
         * default collapsing toolbar background with width = 130 dp
         *
         * @param content is Context, Fragment or Activity
         * @param layoutParams CollapsingToolbarLayout.LayoutParams default is width=match_parent and height=130dp
         *
         * @return FrameLayout
         */
        fun getDefaultBackground(content: Any, layoutParams: CollapsingToolbarLayout.LayoutParams? = null): View {
            val ctx = VS.castToContext(content)

            val view = FrameLayout(ctx)
            view.id = R.id.vs_toolbar_background

            val height = VSUtil.dpToPx(ctx, 130)
            val finalLayoutParams = layoutParams ?: LayoutParams(LayoutParams.MATCH_PARENT, height)
            if (layoutParams == null) {
                finalLayoutParams.collapseMode = LayoutParams.COLLAPSE_MODE_PARALLAX
            }
            view.layoutParams = finalLayoutParams

            return view
        }
    }

}
