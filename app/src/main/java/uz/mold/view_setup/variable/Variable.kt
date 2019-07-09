package uz.mold.view_setup.variable

interface Variable {

    fun getError(): ErrorResult

    fun readyToChange()

    fun modified(): Boolean
}
