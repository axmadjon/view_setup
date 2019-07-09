@file:Suppress("MemberVisibilityCanBePrivate")

package uz.mold.view_setup.widget

import android.content.Context
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import uz.mold.view_setup.R
import uz.mold.view_setup.VS

class UToolbar : Toolbar {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): UToolbar {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * setup view
     *
     * @param title Toolbar title = ResId(Int), CharSequence or String
     * @param subtitle Toolbar subtitle = ResId(Int), CharSequence or String
     * @param navigationIcon Toolbar navigation icon is ResId(Int)
     * @param onNavigationClick Toolbar navigation click listener
     * @param layoutParams ViewGroup.LayoutParams
     *
     * @return UToolbar
     */
    fun setup(
        title: Any? = null,
        subtitle: Any? = null,
        navigationIcon: Int? = null,
        onNavigationClick: (() -> Unit)? = null,
        layoutParams: ViewGroup.LayoutParams? = null
    ): UToolbar {

        title?.let { setViewTitle(it) }

        subtitle?.let { setViewSubtitle(it) }

        layoutParams?.let { setViewLayoutParams(it) }

        navigationIcon?.let { setNavigationIcon(it) }

        onNavigationClick?.let { onClick -> setNavigationOnClickListener { onClick() } }

        return this
    }

    fun setViewLayoutParams(params: ViewGroup.LayoutParams): UToolbar {
        super.setLayoutParams(params)
        return this
    }

    fun setViewTitle(text: Any): UToolbar {
        when (text) {
            is Int -> super.setTitle(text)
            is CharSequence -> super.setTitle(text)
        }
        return this
    }

    fun setViewSubtitle(text: Any): UToolbar {
        when (text) {
            is Int -> super.setSubtitle(text)
            is CharSequence -> super.setSubtitle(text)
        }
        return this
    }

    companion object {

        fun getCollapsingToolbarModePin(content: Any): CollapsingToolbarLayout.LayoutParams {
            val ctx = VS.castToContext(content)

            val tv = TypedValue()

            val width = CollapsingToolbarLayout.LayoutParams.MATCH_PARENT
            var height = CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT

            if (ctx.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                height = TypedValue.complexToDimensionPixelSize(tv.data, ctx.resources.displayMetrics)
            }

            val param = CollapsingToolbarLayout.LayoutParams(width, height)
            param.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN

            return param
        }

        fun create(
            content: Any,
            id: Int = -1,
            title: Any?,
            subtitle: Any? = null,
            layoutParams: ViewGroup.LayoutParams? = null,
            navigationIcon: Int? = null,
            onNavigationClick: (() -> Unit)? = null
        ): UToolbar {
            val view = UToolbar(VS.castToContext(content))
                .setViewId(id)

            view.setup(
                title = title,
                subtitle = subtitle,
                layoutParams = layoutParams
            )

            onNavigationClick?.let { onClick ->
                view.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
                view.setNavigationOnClickListener { onClick() }
            }

            navigationIcon?.let { view.setNavigationIcon(it) }

            return view
        }
    }
}
