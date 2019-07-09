package uz.mold.view_setup.variable

import android.text.TextUtils

class ValueInteger(private val size: Int, private val value: String) : TextValue {
    constructor(size: Int, value: Int?) : this(size, value?.toString() ?: "")

    private var cValue: ValueString = ValueString(size, value)

    override var text: String
        get() = cValue.text
        set(text) {
            cValue.text = text
        }

    val isEmpty: Boolean get() = cValue.isEmpty

    val nonEmpty: Boolean get() = !isEmpty

    fun getValue(): Int? {
        val r = cValue.value
        return if (TextUtils.isEmpty(r)) null else Integer.parseInt(r)
    }

    fun setValue(value: Int?) {
        if (value == null) this.cValue.value = ""
        else this.cValue.value = value.toString()
    }

    override fun readyToChange() {
        this.cValue.readyToChange()
    }

    override fun modified(): Boolean = this.cValue.modified()

    override fun getError(): ErrorResult {
        val r = cValue.getError()
        if (r.isError) {
            return r
        }
        try {
            val q = cValue.value
            if (!TextUtils.isEmpty(q)) {
                Integer.parseInt(q)
            }
        } catch (ex: Exception) {
            return ErrorResult.make(ex)
        }

        return ErrorResult.NONE
    }
}
