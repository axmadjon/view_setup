package uz.mold.view_setup.variable

import android.text.TextUtils

class ValueString(val size: Int, var value: String) : TextValue {

    private var oldValue = ""

    val isEmpty: Boolean get() = TextUtils.isEmpty(value)

    val nonEmpty: Boolean get() = !isEmpty

    override fun readyToChange() {
        oldValue = value
    }

    override fun modified(): Boolean {
        return value != oldValue
    }

    override fun getError(): ErrorResult {
        return if (value.length > size) {
            ErrorResult.make("Text length is greater than $size")
        } else ErrorResult.NONE
    }

    override var text: String
        get() = value
        set(text) {
            value = text
        }
}
