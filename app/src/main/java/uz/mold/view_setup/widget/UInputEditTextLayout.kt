package uz.mold.view_setup.widget

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import uz.mold.view_setup.R
import uz.mold.view_setup.VS
import uz.mold.view_setup.variable.TextValue

class UInputEditTextLayout : TextInputLayout {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun addChild(child: UEditText): UInputEditTextLayout {
        super.addView(child)
        return this
    }


    fun setViewId(id: Int): UInputEditTextLayout {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    fun setup(line: Int, gravity: Int): UInputEditTextLayout {
        if (line > 0) {
            setChildLine(line)
        }

        if (gravity != -1) {
            setChildGravity(gravity)
        }

        return this
    }

    fun setChildLine(line: Int): UInputEditTextLayout {
        val frame = getChildAt(0) as? FrameLayout ?: return this
        val childAt = frame.getChildAt(0) as? UEditText ?: return this
        childAt.setViewLines(line)
        return this
    }

    fun setChildGravity(gravity: Int): UInputEditTextLayout {
        val frame = getChildAt(0) as? FrameLayout ?: return this
        val childAt = frame.getChildAt(0) as? UEditText ?: return this
        childAt.gravity = gravity
        return this
    }

    companion object {

        fun create(
            content: Any,
            resId: Int = -1
        ): UInputEditTextLayout {
            val view = LayoutInflater.from(VS.castToContext(content)).inflate(
                R.layout.mold_input_edittext,
                null
            ) as UInputEditTextLayout
            view.setViewId(resId)
            return view
        }

        fun UInputEditText(
            content: Any,
            id: Int = -1,
            variable: TextValue? = null,
            lines: Int = 1,
            inputType: Int = -1,
            hint: Any? = null,
            backgroundRes: Int = -1,
            rightIconRes: Int = 0,
            leftIconRes: Int = 0,
            gravity: Int = -1,
            touchListener: ((View) -> Unit)? = null,
            layoutParams: ViewGroup.LayoutParams? = null,
            height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            width: Int = ViewGroup.LayoutParams.WRAP_CONTENT
        ): UInputEditTextLayout {
            val view = LayoutInflater.from(VS.castToContext(content)).inflate(
                R.layout.mold_input_edittext,
                null
            ) as UInputEditTextLayout
            val editText = VS.UEditText(
                content = content,
                id = id,
                variable = variable,
                lines = lines,
                inputType = inputType,
                hint = hint,
                backgroundRes = backgroundRes,
                rightIconRes = rightIconRes,
                leftIconRes = leftIconRes,
                gravity = gravity,
                touchListener = touchListener,
                layoutParams = layoutParams,
                width = width, height = height
            )
            view.addChild(editText)
            return view
        }

    }

}
