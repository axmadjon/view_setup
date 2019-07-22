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

/**
 * UTextInputLayout child TextInputLayout
 * this is material design component
 * */
class UTextInputLayout : TextInputLayout {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * fun add child views. the views child EditText
     *
     * @param child child EditText
     *
     * @return UTextInputLayout
     * */
    fun addChild(child: UEditText): UTextInputLayout {
        super.addView(child)
        return this
    }


    fun setViewId(id: Int): UTextInputLayout {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * setup function set view settings
     *
     * @param line child view lines
     * @param gravity child view gravity
     *
     * @return UTextInputLayout
     * */
    fun setup(line: Int, gravity: Int): UTextInputLayout {
        if (line > 0) {
            setChildLine(line)
        }

        if (gravity != -1) {
            setChildGravity(gravity)
        }

        return this
    }

    /**
     *function set first Child View Lines
     *
     *@param line lines child view
     *
     * @return UTextInputLayout
     * */
    fun setChildLine(line: Int): UTextInputLayout {
        val frame = getChildAt(0)as? FrameLayout ?: return this
        val childAt = frame.getChildAt(0) as? UEditText ?: return this
        childAt.setViewLines(line)
        return this
    }

    /**
     *function set first Child View gravity
     *
     * @param gravity lines child view
     *
     * @return UTextInputLayout
     * */
    fun setChildGravity(gravity: Int): UTextInputLayout {
        val frame = getChildAt(0) as? FrameLayout ?: return this
        val childAt = frame.getChildAt(0) as? UEditText ?: return this
        childAt.gravity = gravity
        return this
    }

    companion object {

        /**
         * create UTextInputLayout
         *
         * @param content is Activity or Fragment
         * @param resId is view unique resource id
         *
         * @return UTextInputLayout
         */
        fun create(
            content: Any,
            resId: Int = -1
        ): UTextInputLayout {
            val view = LayoutInflater.from(VS.castToContext(content)).inflate(
                R.layout.mold_input_edittext,
                null
            ) as UTextInputLayout
            view.setViewId(resId)
            return view
        }

        /**
         * this all @params for child view UEditText.
         * */
        fun UInputEditText(
            content: Any,
            id: Int = -1,
            variable: TextValue? = null,
            lines: Int = 1,
            inputType: Int = -1,
            hint: Any? = null,
            backgroundRes: Int = -1,
            rightIconRes: Int = -1,
            leftIconRes: Int = -1,
            gravity: Int = -1,
            onTouchUpListener: ((View) -> Unit)? = null,
            layoutParams: ViewGroup.LayoutParams? = null
        ): UTextInputLayout {
            val view = LayoutInflater.from(VS.castToContext(content)).inflate(
                R.layout.mold_input_edittext,
                null
            ) as UTextInputLayout
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
                onTouchUpListener = onTouchUpListener,
                layoutParams = layoutParams
            )
            view.addChild(editText)
            return view
        }

    }

}
