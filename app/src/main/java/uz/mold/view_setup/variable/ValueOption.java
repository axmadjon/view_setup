package uz.mold.view_setup.variable;


import uz.mold.collection.MyArray;

public class ValueOption<T extends Variable> extends VariableLike {

    public final String title;
    public final ValueBoolean checked = new ValueBoolean();
    public final T valueIfChecked;
    public final Object tag;

    public ValueOption(String title, T value, Object tag) {
        this.title = title;
        this.valueIfChecked = value;
        this.tag = tag;
        if (value == null) {
            throw new RuntimeException();
        }
    }

    public ValueOption(String title, T value) {
        this(title, value, null);
    }

    public T getValue() {
        if (checked.getValue()) {
            return valueIfChecked;
        }
        return null;
    }

    @Override
    protected MyArray<Variable> gatherVariables() {
        return MyArray.from(this.checked, valueIfChecked);
    }

    @Override
    public ErrorResult getError() {
        return valueIfChecked.getError();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(ValueOption filter) {
        if (filter == null) {
            return null;
        }
        return (T) filter.getValue();
    }
}
