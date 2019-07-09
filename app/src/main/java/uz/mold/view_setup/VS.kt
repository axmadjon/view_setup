@file:Suppress("FunctionName", "MemberVisibilityCanBePrivate")

package uz.mold.view_setup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import uz.mold.view_setup.variable.TextValue
import uz.mold.view_setup.variable.ValueBoolean
import uz.mold.view_setup.variable.ValueSpinner
import uz.mold.view_setup.widget.*

@SuppressLint("StaticFieldLeak")
object VS {

    fun castToContext(value: Any): Context {
        return when (value) {
            is Context, is Activity -> value as Context
            is Fragment -> value.activity!!
            else -> throw UnsupportedOperationException("value is not Activity or Fragment")
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Material {@link CoordinatorLayout}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param childs is Array Views
     *
     * @return UCoordinatorLayout child CoordinatorLayout
     */
    fun UCoordinatorLayout(content: Any, id: Int = -1, vararg childs: View): UCoordinatorLayout {
        return UCoordinatorLayout.create(content = castToContext(content), id = id)
            .addChild(*childs)
    }

    /**
     * Material {@link CollapsingToolbarLayout}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param childs is Array Views
     *
     * @return UCollapsingToolbarLayout child CollapsingToolbarLayout
     */
    fun UCollapsingToolbarLayout(content: Any, id: Int = -1, vararg childs: View): UCollapsingToolbarLayout =
        UCollapsingToolbarLayout.create(content = content, id = id)
            .addChild(*childs)

    /**
     * Material {@link AppBarLayout}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param childs is Array Views
     *
     * @return UAppBarLayout child AppBarLayout
     */
    fun UAppBarLayout(content: Any, id: Int = -1, vararg childs: View): UAppBarLayout =
        UAppBarLayout.create(content = content, id = id)
            .addChild(*childs)

    /**
     * Material {@link Toolbar}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param title is Int(ResourceId), CharSequence
     * @param layoutParams ViewGroup.LayoutParams
     *
     * @return UToolbar child Toolbar
     */
    fun UToolbar(
        content: Any,
        id: Int = -1,
        title: Any? = null,
        layoutParams: ViewGroup.LayoutParams? = null
    ): UToolbar =
        UToolbar.create(content = content, id = id, title = title, layoutParams = layoutParams)

    /**
     * Material {@link FloatingActionButton}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param icon is Int(ResourceId), Drawable
     * @param command is function click listener
     *
     * @return UFloatingActionButton child FloatingActionButton
     */
    fun UFloatingActionButton(
        content: Any,
        id: Int = -1,
        icon: Any = R.drawable.ic_add_black_24dp,
        command: () -> Unit
    ): UFloatingActionButton =
        UFloatingActionButton.create(content = content, id = id, icon = icon, command = command)

    /**
     * Material {@link LinearLayout}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param childs is Array Views
     *
     * @return ULinearLayout child LinearLayout(orientation = LinearLayout.HORIZONTAL)
     */
    fun ULinearLayout_Horizontal(content: Any, id: Int = -1, vararg childs: View): ULinearLayout =
        ULinearLayout.create(content = content, id = id, orientation = LinearLayout.HORIZONTAL)
            .addChild(*childs)

    /**
     * Material {@link LinearLayout}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param childs is Array Views
     *
     * @return ULinearLayout child LinearLayout(orientation = LinearLayout.VERTICAL)
     */
    fun ULinearLayout_Vertical(content: Any, id: Int = -1, vararg childs: View): ULinearLayout =
        ULinearLayout.create(content = content, id = id, orientation = LinearLayout.VERTICAL)
            .addChild(*childs)

    /**
     * Material {@link RecyclerView}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param adapter is RecyclerView.Adapter
     *
     * @return URecyclerView child RecyclerView
     */
    fun URecyclerView(content: Any, id: Int = -1, adapter: RecyclerView.Adapter<*>? = null): URecyclerView =
        URecyclerView.create(content = content, id = id, adapter = adapter)

    /**
     * Material {@link NestedScrollView}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     *
     * @return UNestedScrollView child NestedScrollView
     */
    fun UNestedScrollView(content: Any, id: Int = -1): UNestedScrollView =
        UNestedScrollView.create(content = content, id = id)

    /**
     * Material {@link CheckBox}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param variable is Variable by ValueBoolean
     *
     * @return UCheckBox child CheckBox
     */
    fun UCheckBox(content: Any, id: Int = -1, variable: ValueBoolean? = null): UCheckBox =
        UCheckBox.create(content = content, id = id, variable = variable)

    /**
     * Material {@link Spinner}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param variable is Variable by ValueSpinner
     * @param withRightIcon Boolean if show right arrow icon
     *
     * @return USpinner child Spinner
     */
    fun USpinner(content: Any, id: Int = -1, variable: ValueSpinner? = null, withRightIcon: Boolean = true): USpinner =
        USpinner.create(content = content, id = id, variable = variable, withRightIcon = withRightIcon)

    /**
     * Material {@link EditText}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param variable is TextValue
     *
     * @return UEditText child EditText
     */
    fun UEditText(content: Any, id: Int = -1, variable: TextValue? = null): UEditText =
        UEditText.create(content = content, id = id, variable = variable)

    /**
     * Material {@link TextView}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param text is Int(ResourceId) or CharSequence
     *
     * @return UTextView child TextView
     */
    fun UTextView(content: Any, id: Int = -1, text: Any? = null): UTextView =
        UTextView.create(content = content, id = id, text = text)


    // -----------------------------------------------------------------------------------------------------------------
}
