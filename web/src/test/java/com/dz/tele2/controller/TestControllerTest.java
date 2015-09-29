package com.dz.tele2.controller;

import com.dz.tele2.config.routing.Pages;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 * Created by Alex on 28.09.15.
 */

public class TestControllerTest extends ControllerTestBase{

    /**
     * Sample test for controllers
     */

    @Test
    public void testTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.TEST_VIEW))
                .andDo(print());
    }
}
