package uz.mold.view_setup.widget

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.text.InputFilter
import android.util.AttributeSet
import uz.mold.view_setup.UI
import uz.mold.view_setup.VS
import uz.mold.view_setup.setup.Model
import uz.mold.view_setup.variable.TextValue
import uz.mold.view_setup.variable.ValueString

class UEditText : AppCompatEditText {

    val model: Model? get() = UI.getModel(this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setViewId(id: Int): UEditText {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * bind view with variable TextValue
     *
     * @param value TextValue
     *
     * @return UEditText
     */
    fun bind(value: TextValue): UEditText {
        UI.bind(this, value)
        return this
    }

    companion object {

        fun create(content: Any, id: Int = -1, variable: TextValue? = null): UEditText {
            val view = UEditText(context = VS.castToContext(content))
                .setViewId(id)

            if (variable != null) {
                UI.bind(view, variable)

                if (variable is ValueString) {
                    view.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(variable.size))
                }
            }

            return view
        }
    }
}
