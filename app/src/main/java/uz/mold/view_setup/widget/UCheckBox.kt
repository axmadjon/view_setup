package uz.mold.view_setup.widget

import android.content.Context
import android.support.v7.widget.AppCompatCheckBox
import android.util.AttributeSet
import uz.mold.view_setup.UI
import uz.mold.view_setup.VS
import uz.mold.view_setup.setup.Model
import uz.mold.view_setup.variable.ValueBoolean

class UCheckBox : AppCompatCheckBox {

    val model: Model get() = UI.getModel(this)!!

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): UCheckBox {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * set text in checkbox
     *
     * @param text is ResId(Int), CharSequence or String
     *
     * @return UCheckBox
     */
    fun setText(text: Any): UCheckBox {
        when (text) {
            is Int -> super.setText(text)
            is CharSequence -> super.setText(text)
        }
        return this
    }

    /**
     * bind view with variable ValueBoolean
     *
     * @param value ValueBoolean
     *
     * @return UCheckBox
     */
    fun bind(value: ValueBoolean): UCheckBox {
        UI.bind(this, value)
        return this
    }

    companion object {

        /**
         * create UCheckBox
         *
         * @param content is Context, Fragment or Activity
         * @param id resource id
         * @param variable is ValueBoolean default is null
         *
         * @return UCheckBox
         */
        fun create(content: Any, id: Int = -1, variable: ValueBoolean? = null): UCheckBox {
            val view = UCheckBox(VS.castToContext(content))
                .setViewId(id)

            if (variable != null) {
                UI.bind(view, variable)
            }

            return view
        }
    }

}
