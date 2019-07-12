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

    fun setViewLines(lines: Int): UEditText {
        if (lines != -1)
            this.setLines(lines)
        return this
    }

    fun setViewInputType(type: Int): UEditText {
        if (type != -1)
            this.inputType = type
        return this
    }

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


    fun setCompoundDrawablesWithIntrinsicBounds(leftIconRes: Int, rightIconRes: Int): UEditText {
        this.setCompoundDrawablesWithIntrinsicBounds(leftIconRes, 0, rightIconRes, 0)
        return this
    }

    fun setViewBackground(backgroundRes: Int): UEditText {
        if (backgroundRes != -1)
            this.setBackgroundResource(backgroundRes)
        return this
    }

    fun setViewGravity(gravity: Int): UEditText {
        if (gravity != -1)
            this.gravity = gravity
        return this
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setOnTouchListene(touchListener: (View) -> Unit): UEditText {

        this.setOnLongClickListener(null)
        this.setOnKeyListener(null)
        this.setOnTouchListener { v, e ->
            if (e.action == MotionEvent.ACTION_UP) {
                touchListener.invoke(v)
            }
            return@setOnTouchListener false
        }
        return this
    }

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

        fun create(
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
        ): UEditText {
            val view = UEditText(context = VS.castToContext(content))
                .setViewId(id)
                .setViewLines(lines)
                .setViewInputType(inputType)
                .setViewBackground(backgroundRes)
                .setCompoundDrawablesWithIntrinsicBounds(leftIconRes, rightIconRes)
                .setViewGravity(gravity)

            view.setLayoutParamas(layoutParams ?: ViewGroup.LayoutParams(width, height))
            hint?.let { view.setViewHint(it) }
            touchListener?.let { view.setOnTouchListene(it) }

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
