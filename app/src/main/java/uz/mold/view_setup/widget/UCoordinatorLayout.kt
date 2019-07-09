@file:Suppress("FunctionName", "MemberVisibilityCanBePrivate")

package uz.mold.view_setup.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import uz.mold.view_setup.R
import uz.mold.view_setup.VS
import uz.mold.view_setup.VSUtil

class UCoordinatorLayout : CoordinatorLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setViewId(id: Int): UCoordinatorLayout {
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
    fun addChild(vararg childs: View): UCoordinatorLayout {
        for (x in childs) super.addView(x)
        return this
    }


    fun statusBarPadding(): UCoordinatorLayout {
        val left = paddingLeft
        val right = paddingRight
        val bottom = paddingBottom

        val top = VSUtil.getStatusBarHeight(context)

        setPadding(left, top, right, bottom)
        return this
    }

    fun setViewBackground(background: Any): UCoordinatorLayout {
        when (background) {
            is Int -> super.setBackgroundResource(background)
            is Drawable -> super.setBackground(background)
            is String -> super.setBackgroundColor(Color.parseColor(background))
        }
        return this
    }

    companion object {

        fun LayoutParams(
            width: Int = LayoutParams.MATCH_PARENT,
            height: Int = LayoutParams.WRAP_CONTENT
        ): LayoutParams = CoordinatorLayout.LayoutParams(width, height)

        /**
         * create material view UCoordinatorLayout
         *
         * @param content is Context, Fragment or Activity
         * @param layoutParam is CoordinatorLayout.LayoutParam default width=match_parent, height=wrap_parent
         * @param statusBar is Bool default true
         * @return UCoordinatorLayout
         *
         */
        fun create(
            content: Any,
            id: Int = -1,
            layoutParam: ViewGroup.LayoutParams? = null,
            statusBar: Boolean = true
        ): UCoordinatorLayout {
            val view = UCoordinatorLayout(VS.castToContext(content))
                .setViewId(id)

            view.layoutParams = layoutParam ?: ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            if (statusBar) {
                view.statusBarPadding()
            }

            return view
        }

        /**
         * create default UCoordinatorLayout with AppBaLayout and Toolbar
         *
         * @param content is Context, Fragment or Activity
         * @param title is Toolbar title ResId(Int), CharSequence or String
         * @param subtitle is Toolbar subtitle ResId(Int), CharSequence or String
         * @param navigationIcon is ResId(Int) drawable resource
         * @param onNavigationClick is click listener interface
         *
         * @return UCoordinatorLayout
         */
        fun Default_With_AppBarLayout(
            content: Any,
            title: Any,
            subtitle: Any? = null,
            navigationIcon: Int? = null,
            onNavigationClick: (() -> Unit)? = null,
            vararg childs: View
        ): UCoordinatorLayout {
            val toolbar = UAppBarLayout.Default(
                content = content,
                title = title,
                subtitle = subtitle,
                navigationIcon = navigationIcon,
                onNavigationClick = onNavigationClick
            )

            return UCoordinatorLayout.create(content = content)
                .addChild(toolbar)
                .addChild(*childs)
                .setViewBackground(R.color.white)
                .statusBarPadding()
        }
    }
}
