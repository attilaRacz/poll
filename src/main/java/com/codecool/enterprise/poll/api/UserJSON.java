package com.codecool.enterprise.poll.api;

import java.util.ArrayList;
import java.util.List;

class UserJSON {
    private String userName;
    private String email;
    private String password;
    private String passwordCheck;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        values.add(this.email);
        values.add(this.userName);
        values.add(this.password);
        values.add(this.passwordCheck);
        return values;
    }

    public boolean containsEmptyFields() {
        for (String field : getValues()) {
            System.out.println(field);
            if (field == null || field.equals("")) {
                System.out.println("in" + field);
                return true;
            }
        }
        return false;
    }
}
