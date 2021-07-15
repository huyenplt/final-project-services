package vn.co.vis.restful.service;

import vn.co.vis.common.dao.entity.User;
import vn.co.vis.common.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author Giang Thanh Quang
 * @since 10/28/2020
 */

public interface UserService {
    Optional<UserResponse> getUser(String userName);
    Optional<List<UserResponse>> getUsers();
    Optional<UserResponse> createUser(User user);
}
