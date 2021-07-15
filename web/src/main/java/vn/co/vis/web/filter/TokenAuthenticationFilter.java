package vn.co.vis.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import vn.co.vis.web.constant.SessionAttributeName;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Filter authenticate token for system
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    protected ServletContext servletContext;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    public static final String LOCAL = "local";
    public static final String DEV = "dev";
    private static final List<String> PUBLIC_ACCEPTED_URL = List.of(
            "/error",
            "/css",
            "/js",
            "/img",
            "/fonts",
            "/login",
            "/user/create"
    );

    @Value("${spring.profiles.active}")
    private String profileActive;


    /**
     * Override doFilterInternal in OncePerRequestFilter
     *
     * @param httpRequest  current request
     * @param httpResponse current response
     * @param chain        current FilterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws ServletException, IOException {
        String requestUri = httpRequest.getRequestURI();
        if (isPublicRequest(requestUri)) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
        HttpSession session = httpRequest.getSession();
        if (StringUtils.isEmpty(session.getAttribute(SessionAttributeName.TOKEN))) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
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
    private boolean isPublicRequest(String requestURI) {

        String ctxPath = servletContext.getContextPath();
        for (String req : PUBLIC_ACCEPTED_URL) {
            if (requestURI.startsWith(ctxPath + req)) {
                return true;
            }
        }

        return false;

    }
}
