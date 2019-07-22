@file:Suppress("FunctionName", "MemberVisibilityCanBePrivate")

package uz.mold.view_setup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import uz.mold.collection.MyArray
import uz.mold.view_setup.variable.TextValue
import uz.mold.view_setup.variable.UTab
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

    /**
     * Material {@link UToggleButton}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param variable is Variable by ValueBoolean
     *
     * @return UToggleButton child ToggleButton
     */
    fun UToggleButton(content: Any, id: Int = -1, variable: ValueBoolean? = null): UToggleButton =
        UToggleButton.create(content = content, id = id, variable = variable)

    /**
     * Material{@link UButton}
     *
     * @param content is Context, Fragment or Activity
     * @param text is string  text
     * @param textRes is resource text
     * @param command  is button onClick action
     *
     * @return UButton child Button
     */
    fun UButton(content: Any, id: Int = -1, text: Any? = null, command: ((UButton) -> Unit)?)
            : UButton = UButton.create(content = content, id = id, text = text, command = command)

    /**
     * Material {@link EditText}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param variable is TextValue
     * @param lines is editText lines
     * @param hint is Int(ResourceId) or CharSequence
     * @param backgroundRes is Int(ResourceId) editText background resourse
     * @param rightIconRes is view right icon
     * @param leftIconRes is view left icon
     * @param gravity is view gravity
     * @param onTouchUpListener is view touch action listener
     * @param layoutParams is view layoutParams
     *
     * @return UEditText child EditText
     */
    fun UEditText(
        content: Any,
        id: Int = -1,
        variable: TextValue? = null,
        lines: Int = 1,
        inputType: Int = -1,
        hint: Any? = null,
        backgroundRes: Int = -1,
        rightIconRes: Int = -1,
        leftIconRes: Int = -1,
        gravity: Int = -1,
        onTouchUpListener: ((View) -> Unit)? = null,
        layoutParams: ViewGroup.LayoutParams? = null
    ): UEditText = UEditText.create(
        content = content,
        id = id,
        variable = variable,
        lines = lines,
        inputType = inputType,
        hint = hint,
        backgroundRes = backgroundRes,
        rightIconRes = rightIconRes,
        leftIconRes = leftIconRes,
        gravity = gravity,
        onTouchUpListener = onTouchUpListener,
        layoutParams = layoutParams
    )

    /**
     * Material {@link UTextInputLayout}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param childs is  UEditText
     *
     * @return UTextInputLayout child InputEditTextLayout
     */
    fun UInputEditTextLayout(content: Any, id: Int = -1, childs: UEditText): UTextInputLayout =
        UTextInputLayout.create(content = content, resId = id)
            .addChild(childs)


    /**
     * Material {@link UInputEditText With EditText}
     * all @params for child view UEditText
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param variable is TextValue
     * @param lines is editText lines
     * @param hint is Int(ResourceId) or CharSequence
     * @param backgroundRes is Int(ResourceId) editText background resourse
     * @param rightIconRes is view right icon
     * @param leftIconRes is view left icon
     * @param gravity is view gravity
     * @param onTouchUpListener is view touch action listener
     * @param layoutParams is view layoutParams
     *
     * @return UEditText child EditText
     */
    fun UInputEditText(
        content: Any,
        id: Int = -1,
        variable: TextValue? = null,
        lines: Int = 1,
        inputType: Int = -1,
        hint: Any? = null,
        backgroundRes: Int = -1,
        rightIconRes: Int = -1,
        leftIconRes: Int = -1,
        gravity: Int = -1,
        onTouchUpListener: ((View) -> Unit)? = null,
        layoutParams: ViewGroup.LayoutParams? = null
    ): UTextInputLayout = UTextInputLayout.UInputEditText(
        content = content,
        id = id,
        variable = variable,
        lines = lines,
        inputType = inputType,
        hint = hint,
        backgroundRes = backgroundRes,
        rightIconRes = rightIconRes,
        leftIconRes = leftIconRes,
        gravity = gravity,
        onTouchUpListener = onTouchUpListener,
        layoutParams = layoutParams
    )


    /**
     * Material {@link UImageView}
     *
     * @param content is Activity or Fragment
     * @param resId is view unique resource id
     * @param layoutParams Layout Paramas  child ViewGroup.LayoutParams
     * @param src is Int(ResourceId) or String or Bitmap image src
     * @param command is view click action listener
     * @param scaleType is image view scaleType
     * @param dowloandErrorImageRec is image view dowloand error image
     * @param dowloandPlaceHolderImageRec is image view dowloand place holder image
     * @return UImageView child ImageView
     */
    fun UImageView(
        content: Any,
        resId: Int = -1,
        src: Any? = null,
        command: (() -> Unit)? = null,
        scaleType: ImageView.ScaleType? = null,
        dowloandErrorImageRec: Int = -1,
        dowloandPlaceHolderImageRec: Int = -1,
        layoutParams: ViewGroup.LayoutParams? = null
    ): UImageView =
        UImageView.create(
            content = content,
            resId = resId,
            src = src,
            command = command,
            scaleType = scaleType,
            dowloandPlaceHolderImageRec = dowloandPlaceHolderImageRec,
            dowloandErrorImageRec = dowloandErrorImageRec,
            layoutParams = layoutParams

        )

    /**
     * Material {@link Switch}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param variable is Variable by ValueBoolean
     *
     * @return USwitch child Switch
     */
    fun USwitch(content: Any, id: Int = -1, variable: ValueBoolean? = null): USwitch =
        USwitch.create(content = content, resId = id, value = variable)

    /**
     * Material {@link TabLayout}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param selectedListener tabLayout tab select action listener
     * @param tabItems is tab layout tab items
     * @param layoutParams Layout Paramas  child ViewGroup.LayoutParams
     *
     * @return UTabLayout child TabLayout
     */
    fun UTabLayout(
        content: Any,
        @IdRes id: Int = -1,
        selectedListener: TabLayout.BaseOnTabSelectedListener<*>?,
        tabItems: MyArray<UTab>? = null,
        layoutParams: ViewGroup.LayoutParams? = null
    ): UTabLayout = UTabLayout.create(
        content = content,
        id = id,
        selectedListener = selectedListener,
        tabItems = tabItems,
        layoutParams = layoutParams
    )

    /**
     * Material {@link TabLayout With ViewPager}
     *
     * @param content is Activity or Fragment
     * @param id is view unique resource id
     * @param adapter is view pager items adapter
     * @param tabItems is tab layout tab items
     * @param layoutParams Layout Paramas  child ViewGroup.LayoutParams
     *
     * @return UTabLayout child TabLayout
     */
    fun UTabLayoutWithViewPager(
        content: Any,
        @IdRes id: Int = -1,
        adapter: PagerAdapter? = null,
        tabItems: MyArray<UTab>? = null,
        layoutParams: ViewGroup.LayoutParams? = null
    ): UTabLayout = UTabLayout.Default(
        content = content,
        ids = id,
        tabItems = tabItems,
        adapter = adapter,
        layoutParams = layoutParams
    )


    /**
     * Material {@link ViewPager}
     *
     * @param content is Activity or Fragment
     * @param ids is view unique resource id
     * @param layoutParams Layout Paramas  child ViewGroup.LayoutParams
     * @param adapter is view pager items adapter
     * @param changeListener view pager page change listener
     *
     * @return UViewPager child ViewPager
     */
    fun UViewPager(
        content: Any,
        @IdRes ids: Int = -1,
        adapter: PagerAdapter? = null,
        changeListener: ViewPager.OnPageChangeListener? = null,
        layoutParams: ViewGroup.LayoutParams? = null
    ): UViewPager = UViewPager.create(
        content = content,
        ids = ids,
        adapter = adapter,
        changeListener = changeListener,
        layoutParams = layoutParams

    )

    // -----------------------------------------------------------------------------------------------------------------
}
