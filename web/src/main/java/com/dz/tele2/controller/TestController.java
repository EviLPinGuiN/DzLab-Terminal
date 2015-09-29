package com.dz.tele2.controller;

import com.dz.tele2.config.routing.Pages;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alex on 26.09.15.
 */
@Controller
public class TestController extends BaseController{

    /**
     * Test controller just for sample purposes
     */

    private static final Logger LOG = Logger.getLogger(TestController.class);

    @RequestMapping(value = Pages.TEST_URL, method = RequestMethod.GET)
    public String test() throws Exception {
        return Pages.TEST_VIEW;
    }
}
