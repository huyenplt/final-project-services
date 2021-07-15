package vn.co.vis.restful.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.co.vis.common.dao.entity.User;
import vn.co.vis.restful.service.UserService;
import vn.co.vis.common.controller.AbstractController;

/**
 * @author Giang Thanh Quang
 * @since 10/28/2020
 */
@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<UserService> {

    @GetMapping(value = "/{userName}")
    public ResponseEntity<?> getUser(@PathVariable String userName) {
        return response(service.getUser(userName));
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getUsers() {
        return response(service.getUsers());
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return response(service.createUser(user));
    }
}
