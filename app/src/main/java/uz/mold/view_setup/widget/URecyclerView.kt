package uz.mold.view_setup.widget

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import uz.mold.view_setup.VS

class URecyclerView : RecyclerView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    fun setViewId(id: Int): URecyclerView {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * set layoutManager to recyclerView
     *
     * @param spanCount  count span
     *
     * @return URecyclerView
     * */
    fun setGridLayoutManager(spanCount: Int): URecyclerView {
        super.setLayoutManager(GridLayoutManager(context, spanCount))
        return this
    }

    /**
     * set adapter to recyclerView
     *
     * @param  adapter  is child Adapter
     *
     * @return URecyclerView
     * */
    fun setRecyclerAdapter(adapter: Adapter<*>): URecyclerView {
        setAdapter(adapter)
        return this
    }

    companion object {
        /**
         * create URecyclerView
         *
         * @param content is Activity or Fragment
         * @param id is view unique resource id
         * @param adapter is RecyclerView.Adapter
         * @param layoutParams is ViewLayoutParams
         *
         * @return URecyclerView child RecyclerView
         */
        fun create(
            content: Any,
            id: Int = -1,
            layoutParams: CoordinatorLayout.LayoutParams? = null,
            adapter: Adapter<*>? = null
        ): URecyclerView {

            val view = URecyclerView(context = VS.castToContext(content))
                .setViewId(id)
                .setGridLayoutManager(1)

            val finalLayoutParams = layoutParams ?: CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )

            if (layoutParams == null) {
                finalLayoutParams.behavior = AppBarLayout.ScrollingViewBehavior()
            }

            view.layoutParams = finalLayoutParams

            if (adapter != null) view.setRecyclerAdapter(adapter)

            return view
        }
    }


}
