package uz.mold.view_setup.widget

import android.content.Context
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import uz.mold.view_setup.R
import uz.mold.view_setup.UI
import uz.mold.view_setup.VS
import uz.mold.view_setup.setup.ToolbarAction

class UToolbar : Toolbar {

    private var menuActions: MutableList<ToolbarAction> = mutableListOf()

    private var menuIdSeq: Int = 0


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * UToolbar add menu
     *
     * @param title is menu title. Type String or Int (@StringResourse) CharSequence
     * @param iconResourse is menu icon
     * @param command is menu click action
     * @param isSubMenu is menu
     * @param view is toolbar menu view
     *
     * @return UToolbar
     * */
    fun addMenu(
        title: Any,
        iconResourse: Int = -1,
        command: (() -> Unit)? = null,
        isSubMenu: Boolean = false,
        view: View? = null
    ): UToolbar {
        menuActions.add(
            ToolbarAction(
                title = title,
                iconResId = iconResourse,
                command = command,
                submenu = isSubMenu,
                view = view
            )
        )
        return this
    }

    /**UToolbar add menu
     *
     * @param toolbarAction is Toolbar item menu
     *
     * @return UToolbar
     * */
    fun addMenu(toolbarAction: ToolbarAction): UToolbar {
        menuActions.add(toolbarAction)
        return this
    }

    fun create(): UToolbar {
        val menu = menu
        menu.clear()
        if (!hasMoldActionMenus()) return this

        for (m in menuActions) {
            if (m.id == 0) {
                m.id = menuIdSeq++
            }
            val title: CharSequence = when (m.title) {
                is Int -> context.getString(m.title)
                is CharSequence -> m.title
                is String -> m.title
                else -> throw UnsupportedOperationException("text is not Int(String Resource) or CharSequence")
            }
            if (m.submenu) {
                menu.addSubMenu(Menu.NONE, m.id, Menu.NONE, title)
                continue
            }
            val menuItem = menu.add(Menu.NONE, m.id, Menu.NONE, title)
            if (m.view != null) {
                menuItem.actionView = m.view
            } else {
                if (m.iconResId != -1)
                    menuItem.icon = UI.changeDrawableColor(
                        context,
                        m.iconResId,
                        R.color.toolbar_icon_color_silver_dark
                    )
            }
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
        setOnMenuItemClickListener { menuItem ->
            val id = menuItem.itemId
            for (m in menuActions) {
                if (m.id == id && m.command != null) {
                    m.command.invoke()
                    return@setOnMenuItemClickListener true
                }
            }
            false
        }
        return this
    }

    private fun hasMoldActionMenus() = menuActions.isNotEmpty()

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

    /**
     * set layoutParams to view
     *
     * @param params LayoutParams child ViewGroup.LayoutParams
     *
     * @return UToolbar
     * */
    fun setViewLayoutParams(params: ViewGroup.LayoutParams): UToolbar {
        super.setLayoutParams(params)
        return this
    }

    /**
     * set title to toolbar
     *
     * @param text  is Int(ResourceId) or CharSequence
     *
     * @return UToolbar
     * */
    fun setViewTitle(text: Any): UToolbar {
        when (text) {
            is Int -> super.setTitle(text)
            is CharSequence -> super.setTitle(text)
        }
        return this
    }

    /**
     * set subTitle to toolbar
     *
     * @param text  is Int(ResourceId) or CharSequence
     *
     * @return UToolbar
     * */
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

        /**
         * create UToolbar
         *
         * @param content is Activity or Fragment
         * @param id is view unique resource id
         * @param title is Int(ResourceId), CharSequence
         * @param subtitle is Int(ResourceId), CharSequence
         * @param layoutParams ViewGroup.LayoutParams
         * @param navigationIcon is NavigationIcon
         * @param onNavigationClick is Click action listener
         *
         * @return UToolbar
         */
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
