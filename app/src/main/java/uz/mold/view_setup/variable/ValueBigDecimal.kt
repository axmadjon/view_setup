package uz.mold.view_setup.variable

import android.text.TextUtils

import java.math.BigDecimal

class ValueBigDecimal(private val precision: Int, private val scale: Int) : TextValue, Quantity {

    private val cValue: ValueString = ValueString(100, "")
    private var cCache: BigDecimal? = null

    override val quantity: BigDecimal
        get() {
            return getValue() ?: BigDecimal.ZERO
        }

    override var text: String
        get() = cValue.text
        set(text) {
            this.cCache = null
            cValue.text = text
        }

    val isEmpty: Boolean get() = cValue.isEmpty

    val nonEmpty: Boolean get() = !isEmpty

    val isZero: Boolean
        get() {
            if (isEmpty) return true
            val v = getValue()
            return v == null || v.compareTo(BigDecimal.ZERO) == 0
        }

    val nonZero: Boolean
        get() {
            return !isZero
        }

    fun getValue(): BigDecimal? {
        if (cCache == null) {
            val s = cValue.value
            if (!TextUtils.isEmpty(s)) {
                try {
                    cCache = BigDecimal(s)
                } catch (ignore: Exception) {
                }
            }
        }
        return cCache
    }

    fun setValue(value: BigDecimal?) {
        this.cCache = null
        var v = ""
        if (value != null) {
            v = value.toPlainString()
        }
        this.cValue.value = v
    }

    override fun readyToChange() {
        cValue.readyToChange()
    }

    override fun modified(): Boolean {
        return cValue.modified()
    }

    override fun getError(): ErrorResult {
        val v = getValue()
        if (cValue.nonEmpty && v == null) {
            return ErrorResult.make("The field must contain only numeric data.")
        }
        return if (v != null && (v.precision() > precision || v.scale() > scale)) {
            ErrorResult.make("The form is incorrect, the field has the following format $precision,$scale")
        } else ErrorResult.NONE
    }
}
