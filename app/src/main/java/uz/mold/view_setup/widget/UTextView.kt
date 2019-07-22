package uz.mold.view_setup.widget

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import uz.mold.view_setup.VS

class UTextView : AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): UTextView {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * set text to textView
     *
     * @param text  is Int(ResourceId) , CharSequence or String
     *
     * @return UTextView
     * */
    fun setViewText(text: Any? = null): UTextView {
        val str: CharSequence = when (text ?: return this) {
            is Int -> context.getString(text as Int)
            is CharSequence -> text as CharSequence
            is String -> text as String
            else -> throw UnsupportedOperationException("text is not Int(String Resource) or CharSequence")
        }

        super.setText(str)

        return this
    }

    companion object {
        /**
         * create UTextView
         *
         * @param content is Activity or Fragment
         * @param id is view unique resource id
         * @param text is Int(ResourceId), CharSequence or String
         *
         * @return UTextView
         */
        fun create(content: Any, id: Int = -1, text: Any? = null): UTextView =
            UTextView(context = VS.castToContext(content))
                .setViewId(id)
                .setViewText(text)
    }
}
