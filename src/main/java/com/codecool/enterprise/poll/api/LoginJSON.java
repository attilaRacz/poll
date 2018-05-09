package com.codecool.enterprise.poll.api;

class LoginJSON {
    private String email;
    private String password;
    private boolean valid;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
