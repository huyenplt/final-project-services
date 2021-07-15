package vn.co.vis.restful.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.co.vis.common.exception.ResourceNotFoundException;
import vn.co.vis.common.dao.entity.User;
import vn.co.vis.restful.dao.repository.CustomUserRepository;
import vn.co.vis.common.dto.request.LoginRequest;
import vn.co.vis.common.dto.response.LoginResponse;
import vn.co.vis.common.exception.RequestParamInvalidException;
import vn.co.vis.restful.service.AbstractService;
import vn.co.vis.restful.service.LoginService;
import vn.co.vis.restful.service.VerifyService;

import java.util.Optional;


/**
 * @author Giang Thanh Quang
 * @since 10/15/2020
 */
@Service
public class LoginServiceImpl extends AbstractService implements LoginService {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<LoginResponse> login(LoginRequest request) {
        String message = validator.validateRequestThenReturnMessage(request);

        if (!StringUtils.isEmpty(message)) {
            throw new RequestParamInvalidException(message);
        }


        Optional<User> userData = customUserRepository.findByUsername(request.getUserName());

        if (userData.isPresent() && !passwordEncoder.matches(request.getPassword(), userData.get().getPassword())) {
            throw new ResourceNotFoundException();
        }

        return userData.map(user -> new LoginResponse(user.getId(), user.getFirstName(), user.getLastName(),
                verifyService.generateLoginToken(request.getUserName())));
    }
}
