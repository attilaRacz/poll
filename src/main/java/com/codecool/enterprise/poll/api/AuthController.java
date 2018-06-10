package com.codecool.enterprise.poll.api;

import com.codecool.enterprise.poll.model.User;
import com.codecool.enterprise.poll.service.UserService;
import com.codecool.enterprise.poll.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.mindrot.jbcrypt.BCrypt;
import static org.mindrot.jbcrypt.BCrypt.gensalt;
import static org.mindrot.jbcrypt.BCrypt.hashpw;

import static com.codecool.enterprise.poll.util.JsonUtil.toJson;

@RestController
public class AuthController {

    @Autowired
    UserSession session;

    @Autowired
    UserService userService;

    @PostMapping(value = "/api/login")
    public String login(@RequestBody LoginJSON loginData) {
        if (loginWithValidate(loginData.getEmail(), loginData.getPassword()).equals("success")) {
            loginData.setValid(true);
            Long id = userService.findUserByEmail(loginData.getEmail()).getId();
            session.setAttribute("email", loginData.getEmail());
            session.setAttribute("id", String.valueOf(id));
        }
        return toJson(loginData);
    }

    private String loginWithValidate( String email, String password) {
        User user = userService.findUserByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassWord())) {
            session.setAttribute("id", String.valueOf(user.getId()));
            session.setAttribute("email", user.getEmail());
            return "success";
        }
        return "";
    }

    @PostMapping(value = "/api/register")
    public String handleRegisterInput(@RequestBody UserJSON registerData) {
        ErrorJSON result = validateRegister(registerData);
        if (result.isValid()) {
            String hashedPassword = hashpw(registerData.getPassword(), gensalt());
            User user = new User(
                    registerData.getUserName().trim(),
                    registerData.getEmail().trim(),
                    hashedPassword,
                    1,
                    0,
                    0
            );
            userService.addUser(user);
            Long id = userService.findUserByEmail(registerData.getEmail()).getId();
            session.setAttribute("id", String.valueOf(id));
            session.setAttribute("email", registerData.getEmail());
        }
        return toJson(result);
    }

    private ErrorJSON validateRegister(UserJSON json) {
        ErrorJSON errors = new ErrorJSON();
        boolean isvalid = true;

        if (json.containsEmptyFields()) {
            errors.setAllFieldsRequired(true);
            return errors;
        }
        String email = json.getEmail().trim();
        String userName = json.getUserName().trim();
        String password = json.getPassword();
        String passwordCheck = json.getPasswordCheck();

        if (!password.equals(passwordCheck)) {
            errors.setPasswordMismatch(true);
            return errors; // no point going further if passwords are wrong
        }

        if (!userName.matches("[a-zA-Z0-9]+")) {
            errors.setInvalidName(true);
            isvalid = false;
        }

        if (!email.contains("@")) {
            errors.setEmailInvalid(true);
            isvalid = false;
        }

        User potentialUser = userService.findUserByEmail(email);
        if (potentialUser != null) {
            errors.setEmailExists(true);
            isvalid = false;
        }

        errors.setValid(isvalid);
        return errors;
    }
}
