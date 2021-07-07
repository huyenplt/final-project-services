package vn.co.vis.web.service;

import vn.co.vis.common.dto.request.LoginRequest;
import vn.co.vis.common.dto.response.LoginResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Giang Thanh Quang
 * @since 10/15/2020
 */
public interface LoginService {
    Optional<LoginResponse> login(HttpServletRequest httpServletRequest, LoginRequest request);
}
