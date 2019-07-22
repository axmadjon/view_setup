package uz.mold.view_setup.widget

import android.content.Context
import android.support.annotation.IdRes
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.util.AttributeSet
import android.view.ViewGroup
import uz.mold.collection.MyArray
import uz.mold.view_setup.VS
import uz.mold.view_setup.variable.UTab

/**
 * UTabLayout extends TabLayout. Created by @fun create()
 */
class UTabLayout : TabLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * set layoutParams to view
     *
     * @param layoutParams LayoutParams child ViewGroup.LayoutParams
     *
     * @return UTabLayout
     * */
    fun setViewLayoutParams(layoutParams: ViewGroup.LayoutParams?): UTabLayout {
        if (layoutParams != null) {
            this.layoutParams = layoutParams
        }
        return this
    }

    fun setViewId(id: Int): UTabLayout {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * add child view in tabLayout
     *
     * @param tabItems is var myArrays UTab's
     *
     * @return UTabLayout
     */
    fun addTabs(tabItems: MyArray<UTab>): UTabLayout {
        tabItems.forEach { utab ->
            val newTab = this.newTab()
            if (utab.customView == null) {
                utab.text?.let { newTab.setText(getTextToString(it)) }
                utab.icon?.let { newTab.setIcon(it) }
                utab.contentDescription?.let { newTab.setContentDescription(getTextToString(it)) }
            } else {
                newTab.customView = utab.customView
            }
            utab.tag?.let { newTab.setTag(it) }
            this.addTab(newTab)
        }
        return this
    }

    private fun getTextToString(text: Any): CharSequence = when (text) {
        is Int -> context.getString(text)
        is CharSequence -> text
        is String -> text
        else -> throw UnsupportedOperationException("text is not Int(String Resource) or CharSequence")
    }

    /**
     * add child view in tabLayout
     *
     * @param tabItems is var arrays UTab's
     *
     * @return UTabLayout
     */
    fun addTabs(vararg tabItems: UTab): UTabLayout {
        tabItems.forEach { utab ->
            val newTab = this.newTab()
            if (utab.customView == null) {
                utab.text?.let { newTab.setText(getTextToString(it)) }
                utab.icon?.let { newTab.setIcon(it) }
                utab.contentDescription?.let { newTab.setContentDescription(getTextToString(it)) }
            } else {
                newTab.customView = utab.customView
            }
            utab.tag?.let { newTab.setTag(it) }
            this.addTab(newTab)
        }
        return this
    }

    /**
     * add child view in tabLayout
     *
     * @param tabItems is var arrays Tab's
     *
     * @return UTabLayout
     */
    fun addTabs(vararg tabItems: Tab): UTabLayout {
        tabItems.forEach {
            this.addTab(it)
        }
        return this
    }

    /**
     * add view OnTabSelectedListener
     *
     *@param selectedListener tabLayout OnTabSelectedListener Listener
     *
     * @return UTabLayout
     * */
    fun addViewOnTabSelectedListener(selectedListener: BaseOnTabSelectedListener<*>): UTabLayout {
        this.addOnTabSelectedListener(selectedListener)
        return this
    }

    companion object {

        /**
         * create UTabLayout
         *
         * @param content              - for create ViewPager must be @NonNull
         * @param id                   - view ID
         * @param layoutParams         - view layoutParams
         * @param tabItems             - tab Layout items
         * @param selectedListener     - tabLayout OnTabSelectedListener Listener
         *
         * @return UTabLayout
         */
        @JvmOverloads
        fun create(
            content: Any,
            @IdRes id: Int = -1,
            selectedListener: BaseOnTabSelectedListener<*>?,
            tabItems: MyArray<UTab>? = null,
            layoutParams: ViewGroup.LayoutParams? = null
        ): UTabLayout {
            val view = UTabLayout(VS.castToContext(content))
                .setViewId(id)
                .setViewLayoutParams(layoutParams)

            selectedListener?.let { view.addViewOnTabSelectedListener(it) }
            tabItems?.let {
                view.addTabs(it)
            }
            return view
        }

        /**
         * create UTablayout with UViewPager
         *
         * @param content               -for create UViewPager and UTabLayout must be @NonNull
         * @param ids                   - uTabLayout ID
         * @param layoutParams          - view layoutParams
         * @param tabItems              - tab Layout items
         * @param adapter               - ViewPager adapter
         *
         * @return UTabLayout
         */
        @JvmOverloads
        fun Default(
            content: Any,
            @IdRes ids: Int = -1,
            adapter: PagerAdapter? = null,
            tabItems: MyArray<UTab>? = null,
            layoutParams: ViewGroup.LayoutParams? = null
        ): UTabLayout {
            val tabLayout = create(content, ids, null, tabItems, layoutParams)
            val pager =
                VS.UViewPager(
                    content,
                    -1,
                    adapter,
                    TabLayoutOnPageChangeListener(tabLayout),
                    layoutParams
                )
            tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: Tab) {
                    pager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: Tab) {}

                override fun onTabReselected(tab: Tab) {}
            })

            return tabLayout
        }
    }

}

