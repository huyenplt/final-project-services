package vn.co.vis.web.service.impl;

import org.springframework.stereotype.Service;
import vn.co.vis.common.dto.response.UserResponse;
import vn.co.vis.web.service.AbstractService;
import vn.co.vis.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Giang Thanh Quang
 * @since 10/28/2020
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Override
    public Optional<UserResponse> getUser(HttpServletRequest httpServletRequest, String userName) {
        UserResponse response
                = apiExchangeService.get(httpServletRequest, apiExchangeService.createURL(backApiUrl, "users", userName), UserResponse.class);
        if (response == null) {
            return Optional.empty();
        }
        return Optional.of(response);
    }

    @Override
    public Optional<List<UserResponse>> getUsers(HttpServletRequest httpServletRequest) {
        UserResponse[] responses = apiExchangeService.get(httpServletRequest, apiExchangeService.createURL(backApiUrl, "users"), UserResponse[].class);
        if (responses == null || responses.length == 0) {
            return Optional.empty();
        }
        return Optional.of(Arrays.asList(responses));
    }
}
