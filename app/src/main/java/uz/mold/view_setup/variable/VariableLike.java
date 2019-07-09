package uz.mold.view_setup.variable;


import uz.mold.collection.MyArray;

public abstract class VariableLike implements Variable {

    protected abstract MyArray<Variable> gatherVariables();

    @Override
    public void readyToChange() {
        VariableUtil.readyToChange(gatherVariables());
    }

    @Override
    public boolean modified() {
        return VariableUtil.modified(gatherVariables());
    }

    @Override
    public ErrorResult getError() {
        return VariableUtil.getError(gatherVariables());
    }
}
