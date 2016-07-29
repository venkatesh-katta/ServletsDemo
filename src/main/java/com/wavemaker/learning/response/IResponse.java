package com.wavemaker.learning.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by venkateswarluk on 21/7/16.
 */
public interface IResponse<T> {
    void viewRender(HttpServletRequest request,HttpServletResponse response, T t) throws IOException,ServletException;
}
