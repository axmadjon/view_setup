package uz.mold.view_setup.widget

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.ViewGroup
import uz.mold.view_setup.VS

/**
 * UViewPager extends ViewPager. Created by @fun create()
 */
class UViewPager : ViewPager {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    fun setViewId(id: Int): UViewPager {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * set view layoutParams
     *
     * @param params LayoutParams child ViewGroup.LayoutParams
     *
     * @return UViewPager
     * */
    fun setViewLayoutParams(params: ViewGroup.LayoutParams?): UViewPager {
        if (params != null) {
            this.layoutParams = params
        }
        return this
    }

    /**
     * set adapter to ViewPager
     *
     * @param adapter is PagerAdapter
     *
     * @return UViewPager
     * */
    fun setAdapter(adapter: PagerAdapter): UViewPager {
        this.adapter = adapter
        return this
    }

    /**
     * set onPageChangeListene to viewPager
     *
     * @param changeListener  is OnPageChangeListener
     *
     * @return UViewPager
     * */
    fun addViewOnPageChangeListener(changeListener: OnPageChangeListener): UViewPager {
        this.addOnPageChangeListener(changeListener)
        return this
    }

    companion object {

        /**
         * @param content            - for create ViewPager must be @NonNull
         * @param ids            - view ID
         * @param adapter        - View pager adapter for initial viewPager items
         * @param changeListener - Viewpager OnPageChange Listener
         *@param layoutParams    - Layout Paramas  child ViewGroup.LayoutParams
         */
        fun create(
            content: Any,
            @IdRes ids: Int = -1,
            adapter: PagerAdapter? = null,
            changeListener: OnPageChangeListener? = null,
            layoutParams: ViewGroup.LayoutParams? = null
        ): UViewPager {

            val view = UViewPager(VS.castToContext(content))
                .setViewId(ids)
                .setViewLayoutParams(layoutParams)

            adapter?.let { view.setAdapter(it) }
            changeListener?.let { view.addViewOnPageChangeListener(it) }
            return view

        }
    }
}
