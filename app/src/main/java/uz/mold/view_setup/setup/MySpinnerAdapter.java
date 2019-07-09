package uz.mold.view_setup.setup;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import uz.mold.collection.MyArray;
import uz.mold.view_setup.R;
import uz.mold.view_setup.variable.SpinnerOption;

public class MySpinnerAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final MyArray<SpinnerOption> options;
    private final boolean withRightIcon;

    public MySpinnerAdapter(Context ctx, MyArray<SpinnerOption> options, boolean withRightIcon) {
        this.mInflater = LayoutInflater.from(ctx);
        this.options = options;
        this.withRightIcon = withRightIcon;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public SpinnerOption getItem(int position) {
        return options.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private View createView(int position, View convertView, ViewGroup parent, int resource) {
        TextView view = (TextView) convertView;
        if (view == null) {
            view = (TextView) mInflater.inflate(resource, parent, false);
        }
        SpinnerOption item = getItem(position);
        view.setText(item.getName());
        view.setTextColor(Color.parseColor("#545454"));

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int resId = withRightIcon ? R.layout.vs_spiner_item :
                android.R.layout.simple_spinner_item;
        return createView(position, convertView, parent, resId);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent,
                android.R.layout.simple_spinner_dropdown_item);
    }
}
