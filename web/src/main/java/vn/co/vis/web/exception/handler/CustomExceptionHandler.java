package vn.co.vis.web.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import vn.co.vis.common.exception.RequestParamInvalidException;
import vn.co.vis.common.exception.ResourceNotFoundException;
import vn.co.vis.common.exception.TokenInvalidException;

/**
 * Exception handler
 * @author Giang Thanh Quang
 * @since 10/28/2020
 */
@ControllerAdvice
public class CustomExceptionHandler extends DefaultHandlerExceptionResolver {
    @ExceptionHandler({TokenInvalidException.class})
    public ModelAndView handleTokenInvalidException(TokenInvalidException e) {
        return new ModelAndView("redirect:/error/system-error");
    }

    @ExceptionHandler({RequestParamInvalidException.class})
    public ModelAndView handleTokenInvalidException(RequestParamInvalidException e) {
        return new ModelAndView("redirect:/error/system-error");
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ModelAndView("redirect:/error/system-error");
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleCommonException(Exception e) {
        e.printStackTrace();
        return new ModelAndView("redirect:/error/system-error");
    }
}
