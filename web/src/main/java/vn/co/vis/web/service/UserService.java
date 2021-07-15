package vn.co.vis.web.service;

import vn.co.vis.common.dao.entity.User;
import vn.co.vis.common.dto.response.UserResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author Giang Thanh Quang
 * @since 10/28/2020
 */

public interface UserService {
    Optional<UserResponse> getUser(HttpServletRequest httpServletRequest, String userName);

    Optional<List<UserResponse>> getUsers(HttpServletRequest httpServletRequest);

    Optional<UserResponse> createUser(HttpServletRequest httpServletRequest, User user);
}
