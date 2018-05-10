package com.codecool.enterprise.poll.api;

class ErrorJSON {
    private boolean allFieldsRequired;
    private boolean passwordMismatch;
    private boolean invalidName;
    private boolean emailExists;
    private boolean emailInvalid;
    private boolean valid;

    public boolean isAllFieldsRequired() {
        return allFieldsRequired;
    }

    public void setAllFieldsRequired(boolean allFieldsRequired) {
        this.allFieldsRequired = allFieldsRequired;
    }

    public boolean isPasswordMismatch() {
        return passwordMismatch;
    }

    public void setPasswordMismatch(boolean passwordMismatch) {
        this.passwordMismatch = passwordMismatch;
    }

    public boolean isInvalidName() {
        return invalidName;
    }

    public void setInvalidName(boolean invalidName) {
        this.invalidName = invalidName;
    }

    public boolean isEmailExists() {
        return emailExists;
    }

    public void setEmailExists(boolean emailExists) {
        this.emailExists = emailExists;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isEmailInvalid() {
        return emailInvalid;
    }

    public void setEmailInvalid(boolean emailInvalid) {
        this.emailInvalid = emailInvalid;
    }
}
