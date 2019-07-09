@file:Suppress("FunctionName")

package uz.mold.view_setup.widget

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import uz.mold.view_setup.VS

class UAppBarLayout : AppBarLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setViewId(id: Int): UAppBarLayout {
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
    fun addChild(vararg childs: View): UAppBarLayout {
        for (x in childs) super.addView(x)
        return this
    }


    companion object {

        /**
         * AppBarLayout.LayoutParams
         *
         * @param width default MATCH_PARENT
         * @param height default MATCH_PARENT
         *
         * @return AppBarLayout.LayoutParams
         * */
        fun LayoutParams(
            width: Int = LayoutParams.MATCH_PARENT,
            height: Int = LayoutParams.MATCH_PARENT
        ): LayoutParams = AppBarLayout.LayoutParams(width, height)

        /**
         * create UAppBarLayout
         *
         * @param content is Context, Fragment or Activity
         * @param id ResId(Int) resource id
         * @param layoutParams is CoordinatorLayout.LayoutParams
         *
         * @return UAppBarLayout
         */
        fun create(content: Any, id: Int = -1, layoutParams: CoordinatorLayout.LayoutParams? = null): UAppBarLayout {

            val view = UAppBarLayout(context = VS.castToContext(content))
                .setViewId(id)

            view.layoutParams = layoutParams ?: UCoordinatorLayout.LayoutParams()

            return view
        }

        /**
         * default UAppBarLayout
         *
         * @param content is Context, Fragment or Activity
         * @param title is ResId(Int), CharSequence or String
         * @param subtitle is ResId(Int), CharSequence or String
         * @param navigationIcon is ResId(Int) drawable resource
         * @param onNavigationClick is click listener interface
         *
         * @return UAppBarLayout
         */
        fun Default(
            content: Any,
            title: Any? = null,
            subtitle: Any? = null,
            navigationIcon: Int? = null,
            onNavigationClick: (() -> Unit)? = null
        ): UAppBarLayout {

            val defView = UCollapsingToolbarLayout.getDefaultBackground(content)

            val toolbar = UToolbar.create(
                content = content,
                title = title,
                subtitle = subtitle,
                layoutParams = UToolbar.getCollapsingToolbarModePin(content = content),
                navigationIcon = navigationIcon,
                onNavigationClick = onNavigationClick
            )

            val collapsing = UCollapsingToolbarLayout.create(content = content)
                .addChild(defView, toolbar)

            return UAppBarLayout.create(content = content)
                .addChild(collapsing)
        }
    }
}