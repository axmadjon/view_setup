package uz.mold.view_setup.widget

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.text.InputFilter
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
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
     * set Lines
     *
     * @param lines is lines count
     *
     * @return UEditText
     * */
    fun setViewLines(lines: Int): UEditText {
        if (lines != -1)
            this.setLines(lines)
        return this
    }

    /**
     * set inputType
     *
     * @param type is input type
     *
     * @return UEditText
     * */
    fun setViewInputType(type: Int): UEditText {
        if (type != -1)
            this.inputType = type
        return this
    }

    /**
     * set hint
     *
     * @param hint is Int(ResourceId), CharSequence or String
     *
     * @return UEditText
     * */
    fun setViewHint(hint: Any? = null): UEditText {
        val str: CharSequence = when (hint ?: return this) {
            is Int -> context.getString(hint as Int)
            is CharSequence -> hint as CharSequence
            is String -> hint as String
            else -> throw UnsupportedOperationException("text is not Int(String Resource) or CharSequence")
        }
        this.hint = str
        return this
    }


    /**
     * set Left or right icon to uEditText
     *
     * @param leftIconRes is icon resource
     * @param rightIconRes is icon resource
     *
     * @return UEditText
     * */
    fun setCompoundDrawablesWithIntrinsicBounds(leftIconRes: Int = -1, rightIconRes: Int = -1): UEditText {
        this.setCompoundDrawablesWithIntrinsicBounds(
            if (leftIconRes == -1) 0 else leftIconRes,
            0,
            if (rightIconRes == -1) 0 else rightIconRes,
            0
        )
        return this
    }

    /**
     * set background resource  to uEditText
     *
     * @param backgroundRes is background resource
     *
     * @return UEditText
     * */
    fun setViewBackgroundResource(backgroundRes: Int): UEditText {
        if (backgroundRes != -1)
            this.setBackgroundResource(backgroundRes)
        return this
    }

    /**
     * set gravity
     *
     * @param gravity is gravity
     *
     * @return UEditText
     * */
    fun setViewGravity(gravity: Int): UEditText {
        if (gravity != -1)
            this.gravity = gravity
        return this
    }

    /**
     * set OnTouchUpListener
     *
     * @param onTouchUpListener is OnTouchUpListener acrion listener
     *
     * @return UEditText
     * */
    @SuppressLint("ClickableViewAccessibility")
    fun setViewOnTouchUpListener(onTouchUpListener: (View) -> Unit): UEditText {

        this.setOnLongClickListener(null)
        this.setOnKeyListener(null)
        this.setOnTouchListener { v, e ->
            if (e.action == MotionEvent.ACTION_UP) {
                onTouchUpListener.invoke(v)
            }
            return@setOnTouchListener false
        }
        return this
    }

    /**
     * set layoutParams to view
     *
     * @param params LayoutParams child ViewGroup.LayoutParams
     *
     * @return UEditText
     * */
    fun setLayoutParamas(layoutParams: ViewGroup.LayoutParams? = null): UEditText {
        this.layoutParams = layoutParams ?: return this
        return this
    }

    /**
     * bind view with variable TextValue
     *
     * @param value is TextValue
     *
     * @return UEditText
     */
    fun bind(value: TextValue): UEditText {
        UI.bind(this, value)
        return this
    }

    companion object {

        /**
         * Material {@link EditText}
         *
         * @param content is Activity or Fragment
         * @param id is view unique resource id
         * @param variable is TextValue
         * @param lines is editText lines
         * @param hint is Int(ResourceId) or CharSequence
         * @param backgroundRes is Int(ResourceId) editText background resourse
         * @param rightIconRes is view right icon
         * @param leftIconRes is view left icon
         * @param gravity is view gravity
         * @param onTouchUpListener is view touch action listener
         * @param layoutParams is view layoutParams
         *
         * @return UEditText child EditText
         */
        fun create(
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
        ): UEditText {
            val view = UEditText(context = VS.castToContext(content))
                .setViewId(id)
                .setViewLines(lines)
                .setViewInputType(inputType)
                .setViewBackgroundResource(backgroundRes)
                .setCompoundDrawablesWithIntrinsicBounds(leftIconRes, rightIconRes)
                .setViewGravity(gravity)

            if (layoutParams != null)
                view.setLayoutParamas(layoutParams)

            hint?.let { view.setViewHint(it) }
            onTouchUpListener?.let { view.setViewOnTouchUpListener(it) }

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
