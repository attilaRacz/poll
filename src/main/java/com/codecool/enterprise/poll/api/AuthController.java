package com.codecool.enterprise.poll.api;

import com.codecool.enterprise.poll.model.User;
import com.codecool.enterprise.poll.service.UserService;
import com.codecool.enterprise.poll.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        session.clear();
        return "redirect:/";
    }
}
