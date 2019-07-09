package uz.mold.view_setup.setup;

import android.widget.CompoundButton;
import uz.mold.view_setup.variable.ValueBoolean;

public class ListenerCompoundButton implements CompoundButton.OnCheckedChangeListener {

    public final Model model;

    public ListenerCompoundButton(Model model) {
        this.model = model;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ValueBoolean vb = (ValueBoolean) model.value;
        boolean old = vb.getValue();
        vb.setValue(isChecked);
        if (old != vb.getValue()) {
            model.notifyListeners();
        }
    }
}
