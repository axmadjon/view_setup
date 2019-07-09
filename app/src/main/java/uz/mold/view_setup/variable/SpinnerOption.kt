package uz.mold.view_setup.variable

import uz.mold.collection.MyMapper
import java.util.*

class SpinnerOption(val code: String, val name: CharSequence, val tag: Any? = null) {

    override fun toString(): String = name.toString()

    companion object {

        val EMPTY = SpinnerOption("", "")

        val SORT_BY_NAME: Comparator<SpinnerOption> =
            Comparator { l, r -> l.name.toString().compareTo(r.name.toString(), ignoreCase = true) }

        val SORT_DESC_BY_NAME: Comparator<SpinnerOption> =
            Comparator { l, r -> r.name.toString().compareTo(l.name.toString(), ignoreCase = true) }

        val KEY_ADAPTER: MyMapper<SpinnerOption, String> = object : MyMapper<SpinnerOption, String>() {
            override fun apply(option: SpinnerOption): String = option.code
        }
    }
}
