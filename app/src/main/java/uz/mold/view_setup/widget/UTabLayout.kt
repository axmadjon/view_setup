package uz.mold.view_setup.widget

import android.content.Context
import android.support.annotation.IdRes
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.util.AttributeSet
import android.view.ViewGroup
import uz.mold.collection.MyArray
import uz.mold.view_setup.VS
import uz.mold.view_setup.VSUtil

/**
 * UTabLayout extends TabLayout. Created by @fun create()
 */
class UTabLayout : TabLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setSize(height: Int = 0, width: Int = 0): UTabLayout {
        var params: ViewGroup.LayoutParams? = this.layoutParams
        if (params == null) {
            params = ViewGroup.LayoutParams(
                if (width > 0) VSUtil.dpToPx(
                    context,
                    width
                ) else if (width == ViewGroup.LayoutParams.WRAP_CONTENT) ViewGroup.LayoutParams.WRAP_CONTENT else ViewGroup.LayoutParams.MATCH_PARENT,
                if (height > 0) VSUtil.dpToPx(
                    context,
                    height
                ) else if (height == ViewGroup.LayoutParams.WRAP_CONTENT) ViewGroup.LayoutParams.WRAP_CONTENT else ViewGroup.LayoutParams.MATCH_PARENT
            )
        } else {
            if (height != 0)
                params.height = VSUtil.dpToPx(this.context, height)
            if (width != 0)
                params.width = VSUtil.dpToPx(this.context, width)
        }
        this.layoutParams = params
        return this
    }

    fun setViewId(id: Int): UTabLayout {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    fun addTabs(tabItems: MyArray<String>): UTabLayout {
        tabItems.forEach {
            this.addTab(this.newTab().setText(it))
        }
        return this
    }

    fun addTabs(vararg tabItems: String): UTabLayout {
        tabItems.forEach {
            this.addTab(this.newTab().setText(it))
        }
        return this
    }

    fun addTabs(vararg tabItems: Tab): UTabLayout {
        tabItems.forEach {
            this.addTab(it)
        }
        return this
    }

    fun addOnTabSelectedListene(selectedListener: BaseOnTabSelectedListener<*>): UTabLayout {
        this.addOnTabSelectedListener(selectedListener)
        return this
    }

    companion object {

        /**
         * create UTabLayout
         *
         * @param content              - for create ViewPager must be @NonNull
         * @param ids              - view ID
         * @param height           - View Height
         * @param width            - View Width
         * @param selectedListener - TabLayout OnTabSelectedListener Listener
         */
        @JvmOverloads
        fun create(
            content: Any,
            @IdRes id: Int = -1,
            height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
            selectedListener: BaseOnTabSelectedListener<*>?,
            tabItems: MyArray<String>? = null
        ): UTabLayout {
            val view = UTabLayout(VS.castToContext(content))
                .setViewId(id)
                .setSize(height, width)

            selectedListener?.let { view.addOnTabSelectedListene(it) }
            tabItems?.let {
                view.addTabs(it)
            }
            return view
        }

        /**
         * create UTablayout with UViewPager
         *
         * @param content     -for create UViewPager and UTabLayout must be @NonNull
         * @param ids     - uTabLayout ID
         * @param height  - View Height
         * @param width   - View Width
         * @param adapter - ViewPager adapter
         */
        @JvmOverloads
        fun Default(
            content: Any,
            @IdRes ids: Int = -1,
            height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
            adapter: PagerAdapter? = null,
            tabItems: MyArray<String>? = null
        ): UTabLayout {
            val tabLayout = create(content, ids, ViewGroup.LayoutParams.WRAP_CONTENT, width, null, tabItems)
            val pager =
                VS.UViewPager(
                    content,
                    -1,
                    height,
                    width,
                    adapter,
                    TabLayoutOnPageChangeListener(tabLayout)
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

