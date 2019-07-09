package uz.mold.view_setup.variable;

import android.text.TextUtils;

public abstract class ErrorResult {

    public abstract boolean isError();

    public boolean nonError() {
        return !isError();
    }

    public abstract String getErrorMessage();

    public abstract Exception getException();

    public ErrorResult or(ErrorResult that) {
        if (this.isError()) {
            return this;
        } else if (that != null){
            return that;
        }
        return NONE;
    }

    public ErrorResult or(Variable that) {
        if (this.isError()) {
            return this;
        } else if (that != null) {
            return that.getError();
        }
        return NONE;
    }

    public static ErrorResult make(String errorMessage) {
        if (TextUtils.isEmpty(errorMessage)) {
            throw new IllegalArgumentException();
        } else {
            return new Error(errorMessage, null);
        }
    }

    public static ErrorResult make(Exception ex) {
        if (ex == null) {
            throw new IllegalArgumentException();
        } else {
            return new Error(String.valueOf(ex.getMessage()), ex);
        }
    }

    public static ErrorResult make(Variable... variables) {
        for (Variable variable : variables) {
            ErrorResult r = variable.getError();
            if (r.isError()) {
                return r;
            }
        }
        return ErrorResult.NONE;
    }

    public static final ErrorResult NONE = new ErrorResult() {
        @Override
        public boolean isError() {
            return false;
        }

        @Override
        public String getErrorMessage() {
            return null;
        }

        @Override
        public Exception getException() {
            return null;
        }
    };

    private static class Error extends ErrorResult {

        private final String errorMessage;
        private final Exception exception;

        public Error(String errorMessage, Exception exception) {
            this.errorMessage = errorMessage;
            this.exception = exception;
        }

        @Override
        public boolean isError() {
            return true;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }

        @Override
        public Exception getException() {
            return exception;
        }
    }

}
