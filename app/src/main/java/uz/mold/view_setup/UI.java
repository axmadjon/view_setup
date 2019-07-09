package uz.mold.view_setup;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import uz.mold.view_setup.setup.*;
import uz.mold.view_setup.variable.TextValue;
import uz.mold.view_setup.variable.ValueBoolean;
import uz.mold.view_setup.variable.ValueSpinner;

public class UI {

    //----------------------------------------------------------------------------------------------

    public static void hideSoftKeyboard(@NonNull Activity activity) {
        View focus = activity.getCurrentFocus();
        if (focus == null) return;
        InputMethodManager ims = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (ims != null) ims.hideSoftInputFromWindow(focus.getWindowToken(), 0);
    }

    //----------------------------------------------------------------------------------------------

    @Nullable
    public static Drawable changeDrawableColor(@NonNull Context ctx, @DrawableRes int resId, @ColorRes int resColorId) {
        if (resId == 0 || resColorId == 0) return null;

        Resources res = ctx.getResources();
        Drawable drawable = res.getDrawable(resId);
        Drawable.ConstantState constantState = drawable.getConstantState();

        if (constantState == null) return null;

        drawable = constantState.newDrawable().mutate();
        int color = res.getColor(resColorId);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    //----------------------------------------------------------------------------------------------

    public static void setMargins(@NonNull View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    //----------------------------------------------------------------------------------------------

    public static void setElevation(@Nullable View view, float elevation) {
        if (view != null) {
            ViewCompat.setElevation(view, elevation);
            ViewCompat.setTranslationZ(view, elevation);
        }
    }

    //----------------------------------------------------------------------------------------------

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    //----------------------------------------------------------------------------------------------

    public static void bind(EditText et, TextValue value) {
        ListenerEditText tag = null;
        if (et.getTag() instanceof ListenerEditText) {
            tag = (ListenerEditText) et.getTag();
            et.removeTextChangedListener(tag);
        }

        et.setText(value.getText());

        Model model = new Model(value);
        tag = new ListenerEditText(model);

        et.setTag(tag);
        et.addTextChangedListener(tag);
    }

    public static Model getModel(EditText et) {
        if (et.getTag() instanceof ListenerEditText) {
            ListenerEditText tag = (ListenerEditText) et.getTag();
            return tag.model;
        }
        return null;
    }

    public static void bind(CompoundButton cb, ValueBoolean value) {
        cb.setOnCheckedChangeListener(null);
        cb.setTag(null);

        cb.setChecked(value.getValue());

        Model model = new Model(value);
        cb.setTag(model);
        cb.setOnCheckedChangeListener(new ListenerCompoundButton(model));
    }

    public static Model getModel(CompoundButton cb) {
        if (cb.getTag() instanceof Model) {
            return (Model) cb.getTag();
        }
        return null;
    }

    public static void bind(Spinner sp, ValueSpinner value) {
        bind(sp, value, false);
    }

    public static void bind(Spinner sp, ValueSpinner value, boolean withRightIcon) {
        sp.setAdapter(new MySpinnerAdapter(sp.getContext(), value.getOptions(), withRightIcon));

        sp.setOnItemSelectedListener(null);

        sp.setSelection(value.getPosition());

        sp.setOnItemSelectedListener(new ListenerSpinner(new Model(value)));
    }

    public static Model getModel(Spinner sp) {
        AdapterView.OnItemSelectedListener onItemSelectedListener = sp.getOnItemSelectedListener();
        if (onItemSelectedListener instanceof ListenerSpinner) {
            return ((ListenerSpinner) onItemSelectedListener).model;
        }
        return null;
    }

    //----------------------------------------------------------------------------------------------
}
