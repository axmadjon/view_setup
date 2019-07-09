package uz.mold.view_setup.widget

import android.content.Context
import android.support.v7.widget.AppCompatSpinner
import android.util.AttributeSet
import uz.mold.view_setup.UI
import uz.mold.view_setup.VS
import uz.mold.view_setup.setup.Model
import uz.mold.view_setup.variable.ValueSpinner

class USpinner : AppCompatSpinner {

    val model: Model? get() = UI.getModel(this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): USpinner {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * bind view with variable ValueSpinner
     *
     * @param value ValueSpinner
     * @param withRightIcon is Boolean
     *
     * @return USpinner
     */
    fun bind(value: ValueSpinner, withRightIcon: Boolean): USpinner {
        UI.bind(this, value, withRightIcon)
        return this
    }

    companion object {

        fun create(
            content: Any,
            id: Int = -1,
            variable: ValueSpinner? = null,
            withRightIcon: Boolean = false
        ): USpinner {

            val view = USpinner(context = VS.castToContext(content))
                .setViewId(id)

            if (variable != null) {
                UI.bind(view, variable, withRightIcon)
            }

            return view
        }
    }
}
