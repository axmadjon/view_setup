package uz.mold.view_setup.widget

import android.content.Context
import android.support.v7.widget.SwitchCompat
import android.util.AttributeSet
import uz.mold.view_setup.UI
import uz.mold.view_setup.VS
import uz.mold.view_setup.setup.Model
import uz.mold.view_setup.variable.ValueBoolean

class USwitch : SwitchCompat {

    val model: Model? get() = UI.getModel(this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun bind(value: ValueBoolean): USwitch {
        UI.bind(this, value)
        return this
    }

    fun setViewId(id: Int): USwitch {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    companion object {

        fun create(
            content: Any,
            resId: Int = -1,
            value: ValueBoolean? = null
        ): USwitch {

            val view = USwitch(VS.castToContext(content))
                .setViewId(resId)

            value?.let { view.bind(value) }
            return view
        }
    }

}
