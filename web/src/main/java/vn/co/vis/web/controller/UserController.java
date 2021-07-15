package vn.co.vis.web.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.co.vis.common.controller.AbstractController;
import vn.co.vis.common.dao.entity.User;
import vn.co.vis.common.dto.response.UserResponse;
import vn.co.vis.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Giang Thanh Quang
 * @since 10/28/2020
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<UserService> {

    @GetMapping(value = "")
    public ModelAndView getUsers(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("userList", service.getUsers(httpServletRequest).get());
        return modelAndView;
    }

    @GetMapping(value = "/detail")
    public ModelAndView getUsers(HttpServletRequest httpServletRequest, @RequestParam(name = "user-name") String userName) {
        ModelAndView modelAndView = new ModelAndView("user-detail");
        modelAndView.addObject("userInfo", service.getUser(httpServletRequest, userName).get());

        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView createUser() {
        return new ModelAndView("user/user-create");
    }

    @PostMapping(value = "/create")
    public ModelAndView createUser(HttpServletRequest httpServletRequest) {
        User user = new User(
                httpServletRequest.getParameter("firstName"),
                httpServletRequest.getParameter("lastName"),
                httpServletRequest.getParameter("username"),
                httpServletRequest.getParameter("password"),
                httpServletRequest.getParameter("phone"),
                httpServletRequest.getParameter("email")
        );

        Optional<UserResponse> response = service.createUser(httpServletRequest, user);

        if (response.isEmpty()) {
            return new ModelAndView("redirect:/error/system-error");
        }
        return new ModelAndView("redirect:/login");
    }
}
