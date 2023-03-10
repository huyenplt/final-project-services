package vn.co.vis.restful.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import vn.co.vis.common.constant.HttpMethod;
import vn.co.vis.common.exception.TokenInvalidException;
import vn.co.vis.restful.service.VerifyService;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filter authenticate token for system
 *
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    protected ServletContext servletContext;

    @Autowired
    private VerifyService verifyService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    public static final String LOCAL = "local";
    public static final String DEV = "dev";
    private static final Map<String, String> PUBLIC_ACCEPTED_URL = new HashMap<>() {{
        put("/login", HttpMethod.POST);
        put("/users", HttpMethod.POST);
    }};

    @Value("${spring.profiles.active}")
    private String profileActive;



    /**
     * Override doFilterInternal in OncePerRequestFilter
     *
     * @param httpRequest current request
     * @param httpResponse    current response
     * @param chain       current FilterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws ServletException, IOException {
        String requestURI = httpRequest.getRequestURI();
        String requestMethod = httpRequest.getMethod();
        if (isPubicRequest(requestURI, requestMethod)) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
        String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (StringUtils.isEmpty(token)) {
                throw new TokenInvalidException("E04", "token is empty!");
            }
            verifyService.verifyLoginToken(token);
        } catch (Exception e) {
            resolver.resolveException(httpRequest, httpResponse, null, e);
            return;
        }

        chain.doFilter(httpRequest, httpResponse);
    }

    /**
     * Accept all public request and swagger request to test api
     *
     * @param requestURI current request uri
     * @return boolean
     */
    private boolean isPubicRequest(String requestURI, String requestMethod) {

        String ctxPath = servletContext.getContextPath();
        for (Map.Entry<String, String> entry : PUBLIC_ACCEPTED_URL.entrySet()) {
            if (requestURI.startsWith(ctxPath + entry.getKey()) && requestMethod.equals(entry.getValue())) {
                return true;
            }
        }

        if (profileActive.equals(LOCAL) || profileActive.equals(DEV)) {
            List<String> swaggerUriAccepted = List.of(
                    "/configuration/ui",
                    "/swagger-resources",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/v2/api-docs",
                    "/webjars",
                    "/images"
            );
            for (String req : swaggerUriAccepted) {
                if (requestURI.startsWith(ctxPath + req)) {
                    return true;
                }
            }
        }
        return false;

    }
}
