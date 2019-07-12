package uz.mold.view_setup.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ToggleButton
import uz.mold.view_setup.UI
import uz.mold.view_setup.VS
import uz.mold.view_setup.setup.Model
import uz.mold.view_setup.variable.ValueBoolean

class UToggleButton : ToggleButton {

    val model: Model get() = UI.getModel(this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * bind view with variable UToggleButton
     *
     * @param value ValueBoolean
     *
     * @return UToggleButton
     */
    fun bind(variable: ValueBoolean): UToggleButton {
        UI.bind(this, variable)
        return this
    }

    fun setViewId(id: Int): UToggleButton {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    companion object {

        /**
         * create UToggleButton
         *
         * @param content is Context, Fragment or Activity
         * @param id resource id
         * @param variable is ValueBoolean default is null
         *
         * @return UToggleButton child ToggleButton
         */
        fun create(
            content: Any,
            id: Int = -1,
            variable: ValueBoolean? = null
        ): UToggleButton {
            val view = UToggleButton(VS.castToContext(content))
                .setViewId(id)

            variable.let { UI.bind(view, it) }

            return view
        }
    }

}
