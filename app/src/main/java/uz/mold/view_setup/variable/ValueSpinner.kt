package uz.mold.view_setup.variable

import android.text.TextUtils
import uz.mold.collection.MyArray
import uz.mold.collection.MyPredicate

class ValueSpinner(val options: MyArray<SpinnerOption>, var value: SpinnerOption = options.get(0)) : TextValue {

    private var oldValue: SpinnerOption? = null

    init {
        this.oldValue = value
    }

    fun getPosition(): Int {
        return options.findFirstPosition(object : MyPredicate<SpinnerOption>() {
            override fun apply(option: SpinnerOption): Boolean = option == value
        })
    }

    val isEmpty: Boolean get() = TextUtils.isEmpty(value.code)

    val nonEmpty: Boolean get() = !isEmpty

    override fun readyToChange() {
        oldValue = value
    }

    override fun modified(): Boolean = oldValue !== value

    override fun getError(): ErrorResult = ErrorResult.NONE

    override var text: String
        get() = value.code
        set(text) {
            this.value = options.find(text, SpinnerOption.KEY_ADAPTER)
        }

}