package vn.co.vis.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.co.vis.common.dto.request.LoginRequest;
import vn.co.vis.common.dto.response.LoginResponse;
import vn.co.vis.common.exception.RequestParamInvalidException;
import vn.co.vis.web.constant.SessionAttributeName;
import vn.co.vis.web.service.AbstractService;
import vn.co.vis.web.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


/**
 * @author Giang Thanh Quang
 * @since 10/15/2020
 */
@Service
public class LoginServiceImpl extends AbstractService implements LoginService {

    @Override
    public Optional<LoginResponse> login(HttpServletRequest httpServletRequest, LoginRequest request) {
        if (request == null) {
            throw new RequestParamInvalidException("request parameter can not be null");
        }
        String message = validator.validateRequestThenReturnMessage(request);
        if (!StringUtils.isEmpty(message)) {
            throw new RequestParamInvalidException(message);
        }
        LoginResponse response = apiExchangeService.post(httpServletRequest, backApiUrl + "/login", request, LoginResponse.class);
        if (response == null) {
            return Optional.empty();
        }

        httpServletRequest.getSession().setAttribute(SessionAttributeName.TOKEN, response.getToken());
        return Optional.of(response);
    }
}
