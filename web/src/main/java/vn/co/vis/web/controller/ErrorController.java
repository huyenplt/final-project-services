package vn.co.vis.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.co.vis.common.controller.AbstractController;
import vn.co.vis.web.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for error page
 *
 * @author Giang Thanh Quang
 * @since 10/30/2020
 */
@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractController<UserService> {
    @GetMapping(value = "/system-error")
    public ModelAndView getUsers(HttpServletRequest httpServletRequest) {
        return new ModelAndView("error-page");
    }
}
