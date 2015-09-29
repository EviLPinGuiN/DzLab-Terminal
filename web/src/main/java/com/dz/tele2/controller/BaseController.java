package com.dz.tele2.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alex on 26.09.15.
 */
public class BaseController {

    /**
     * Base controller class. All application`s controllers should extend
     * this class.
     */

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;
}
