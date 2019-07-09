package uz.mold.view_setup.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import uz.mold.view_setup.VS

class ULinearLayout : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): ULinearLayout {
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
    fun addChild(vararg childs: View): ULinearLayout {
        for (x in childs) super.addView(x)
        return this
    }


    fun create(): View = this

    companion object {

        fun create(content: Any, id: Int = -1, orientation: Int = VERTICAL): ULinearLayout {
            val view = ULinearLayout(context = VS.castToContext(content))
                .setViewId(id)
            view.orientation = orientation
            return view
        }
    }
}
