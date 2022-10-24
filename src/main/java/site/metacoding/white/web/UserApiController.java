package site.metacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.User;
import site.metacoding.white.service.UserService;

@RequiredArgsConstructor
@RestController // JSON return할 것
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    // JoinDto
    @PostMapping("/join")
    public String save(@RequestBody User user) { // JSON으로 받을거니까 request body를 붙임
        userService.save(user);
        return "ok";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User principal = userService.login(user);
        session.setAttribute("principal", principal);
        return "ok";
    }

}
