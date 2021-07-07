package vn.co.vis.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import vn.co.vis.common.exception.ResourceNotFoundException;
import vn.co.vis.common.log.AppLogger;
import vn.co.vis.common.log.LoggerFactory;
import vn.co.vis.common.log.LoggerType;

import java.util.Optional;

public abstract class AbstractController<S> {
    @Autowired
    protected S service;

    protected static final AppLogger APP_LOGGER = LoggerFactory.getLogger(LoggerType.APPLICATION);

    /**
     * @param response Optional of response object
     * @param <T>      Class type
     * @return ResponseEntity
     */
    protected <T> ResponseEntity<T> response(Optional<T> response) {
        return new ResponseEntity<T>(response.orElseThrow(() -> {
            throw new ResourceNotFoundException();
        }), HttpStatus.OK);
    }
}
