package uz.mold.view_setup;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import uz.mold.view_setup.setup.Model;
import uz.mold.view_setup.variable.TextValue;
import uz.mold.view_setup.variable.ValueBoolean;
import uz.mold.view_setup.variable.ValueSpinner;
import uz.mold.view_setup.widget.URecyclerView;

public class ViewSetup {

    @NonNull
    public final View view;

    private SparseArray<View> cachedViews = new SparseArray<>();

    public ViewSetup(@NonNull View parent) {
        this.view = parent;
    }

    public ViewSetup(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, int layoutId) {
        this(inflater.inflate(layoutId, container, false));
    }

    public ViewSetup(@NonNull LayoutInflater inflater, int layoutId) {
        this(inflater.inflate(layoutId, null, false));
    }

    public ViewSetup(@NonNull Context context, @LayoutRes int layoutId) {
        this(LayoutInflater.from(context), null, layoutId);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public <T extends View> T id(@IdRes int resId) {
        View v = cachedViews.get(resId);
        if (v == null) {
            v = view.findViewById(resId);
            if (v == null) {
                throw new NullPointerException("view not found, is null, check resource id");
            }
            cachedViews.put(resId, v);
        }
        return (T) v;
    }

    @NonNull
    public TextView textView(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public ImageView imageView(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public EditText editText(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public RatingBar ratingBar(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public Button button(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public ImageButton imageButton(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public <T extends CompoundButton> T compoundButton(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public Spinner spinner(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public <T extends ViewGroup> T viewGroup(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public RecyclerView recyclerView(@IdRes int resId) {
        return id(resId);
    }

    @NonNull
    public URecyclerView URecyclerView(@IdRes int resId) {
        return id(resId);
    }

    public void bind(int resId, TextValue value) {
        UI.bind(editText(resId), value);
    }

    public void bind(int resId, ValueBoolean value) {
        UI.bind(compoundButton(resId), value);
    }

    public void bind(int resId, ValueSpinner value) {
        UI.bind(spinner(resId), value);
    }

    public Model model(int resId) {
        View v = id(resId);
        if (v instanceof EditText) {
            return UI.getModel((EditText) v);

        } else if (v instanceof CompoundButton) {
            return UI.getModel((CompoundButton) v);

        } else if (v instanceof Spinner) {
            return UI.getModel((Spinner) v);

        } else {
            return null;
        }
    }
}
