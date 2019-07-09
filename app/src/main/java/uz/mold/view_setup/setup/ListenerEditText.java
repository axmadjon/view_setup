package uz.mold.view_setup.setup;

import android.text.Editable;
import android.text.TextWatcher;

public class ListenerEditText implements TextWatcher {

    public final Model model;

    public ListenerEditText(Model model) {
        this.model = model;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        model.setValue(s.toString());
    }
}
