package com.wavemaker.learning.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by venkateswarluk on 21/7/16.
 */
public interface IService<T> {
    T doAction(List<String> params, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
