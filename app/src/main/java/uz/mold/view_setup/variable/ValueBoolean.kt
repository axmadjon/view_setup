package uz.mold.view_setup.variable

class ValueBoolean(var value: Boolean = false) : TextValue {

    private var oldValue: Boolean = false

    override var text: String
        get() = if (value) "1" else "0"
        set(text) {
            value = "1" == text
        }

    override fun readyToChange() {
        oldValue = value
    }

    override fun modified(): Boolean = oldValue != value

    override fun getError(): ErrorResult = ErrorResult.NONE
}
