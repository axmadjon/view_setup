package uz.mold.view_setup.variable


import uz.mold.collection.MyArray
import uz.mold.collection.MyPredicate

class ValueArray<E : Variable>(private var items: MyArray<E> = MyArray.emptyArray()) : Variable {

    private var changed = false

    fun prepend(item: E): ValueArray<E> {
        this.items = items.prepend(item)
        this.changed = true
        return this
    }

    fun append(item: E): ValueArray<E> {
        this.items = items.append(item)
        this.changed = true
        return this
    }

    fun delete(item: E): ValueArray<E> {
        this.items = items.filter(object : MyPredicate<E>() {
            override fun apply(e: E): Boolean {
                return e !== item
            }
        })
        this.changed = true
        return this
    }

    override fun readyToChange() {
        changed = false
        VariableUtil.readyToChange(items)
    }

    override fun modified(): Boolean {
        return if (changed) true
        else VariableUtil.modified(items)
    }

    override fun getError(): ErrorResult = VariableUtil.getError(items)

}
