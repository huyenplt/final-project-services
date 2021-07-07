package vn.co.vis.web.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import vn.co.vis.common.exchange.ApiExchangeService;
import vn.co.vis.common.utility.ObjectValidator;

import javax.annotation.PostConstruct;

/**
 * Common attribute or method for service
 * @author Giang Thanh Quang
 * @since 10/15/2020
 */
public abstract class AbstractService {
    @Autowired
    protected Environment env;

    @Autowired
    protected ObjectValidator validator;

    @Autowired
    protected ApiExchangeService apiExchangeService;

    protected ObjectMapper objectMapper;

    protected String backApiUrl;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        backApiUrl = env.getProperty("api.backend.url");
    }
}
