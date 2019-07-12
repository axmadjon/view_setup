package uz.mold.view_setup.widget

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import uz.mold.view_setup.VS
import uz.mold.view_setup.VSUtil

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

    fun setSize(height: Int = 0, width: Int = 0): UViewPager {
        var params: ViewGroup.LayoutParams? = this.layoutParams
        if (params == null) {
            params = LayoutParams(
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

    fun setAdapter(adapter: PagerAdapter): UViewPager {
        this.adapter = adapter
        return this
    }

    fun addOnPageChangeListene(changeListener: OnPageChangeListener): UViewPager {
        this.addOnPageChangeListener(changeListener)
        return this
    }

    companion object {

        /**
         * @param ctx            - for create ViewPager must be @NonNull
         * @param ids            - view ID
         * @param height         - View Height
         * @param width          - View Width
         * @param adapter        - View pager adapter for initial viewPager items
         * @param changeListener - Viewpager OnPageChange Listener
         */
        fun create(
            content: Any,
            @IdRes ids: Int = -1,
            height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
            adapter: PagerAdapter? = null,
            changeListener: OnPageChangeListener? = null
        ): UViewPager {

            val view = UViewPager(VS.castToContext(content))
                .setViewId(ids)
                .setSize(height, width)

            adapter?.let { view.setAdapter(it) }
            changeListener?.let { view.addOnPageChangeListene(it) }
            return view

        }
    }
}
