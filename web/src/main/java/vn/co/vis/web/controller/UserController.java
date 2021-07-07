package vn.co.vis.web.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.co.vis.web.service.UserService;

import javax.servlet.http.HttpServletRequest;

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
}
