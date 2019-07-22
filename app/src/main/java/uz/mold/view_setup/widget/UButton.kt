package uz.mold.view_setup.widget

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import uz.mold.view_setup.VS

class UButton : AppCompatButton {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): UButton {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * UButton set text
     *
     * @param text is UButton text
     *
     * @return UButton
     * */
    fun setViewText(text: Any? = null): UButton {
        val str: CharSequence = when (text ?: return this) {
            is Int -> context.getString(text as Int)
            is CharSequence -> text as CharSequence
            is String -> text as String
            else -> throw UnsupportedOperationException("text is not Int(String Resource) or CharSequence")
        }

        super.setText(str)

        return this
    }

    /**
     * setOnclickListener function detect click action
     * @param command is click listener interface
     *
     * @return UButton
     * */
    fun setViewOnClickListener(command: (UButton) -> Unit): UButton {
        this.setOnClickListener { v -> command.invoke(v as UButton) }
        return this
    }

    companion object {
        /**
         * create UButton
         *
         * @param content is Context, Fragment or Activity
         * @param text is UButton text
         * @param command  is button onClick action
         *
         * @return UButton
         */
        fun create(
            content: Any,
            id: Int = -1,
            text: Any? = null,
            command: ((UButton) -> Unit)?
        ): UButton {
            val view = UButton(VS.castToContext(content)).setViewId(id)
            text?.let { view.setViewText(it) }
            command?.let { view.setViewOnClickListener(it) }
            return view
        }
    }
}
