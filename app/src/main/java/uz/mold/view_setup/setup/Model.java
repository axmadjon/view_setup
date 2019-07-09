package uz.mold.view_setup.setup;


import uz.mold.view_setup.variable.TextValue;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public final TextValue value;

    private List<ModelChange> onChangeListeners;

    public Model(TextValue value) {
        this.value = value;
    }

    public Model notifyListeners() {
        if (onChangeListeners != null) {
            for (ModelChange ch : onChangeListeners) {
                ch.onChange();
            }
        }
        return this;
    }

    public Model setValue(String text) {
        String old = value.getText();
        value.setText(text);
        if (!old.equals(value.getText())) {
            notifyListeners();
        }
        return this;
    }

    public Model add(ModelChange onChange) {
        if (onChangeListeners == null) {
            onChangeListeners = new ArrayList<>();
        }
        for (ModelChange ch : onChangeListeners) {
            if (ch == onChange) {
                return this;
            }
        }
        onChangeListeners.add(onChange);
        return this;
    }

    public Model remove(ModelChange onChange) {
        if (onChangeListeners == null) {
            return this;
        }
        onChangeListeners.remove(onChange);
        return this;
    }

}
