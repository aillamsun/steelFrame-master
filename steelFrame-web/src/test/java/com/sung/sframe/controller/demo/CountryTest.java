package com.sung.sframe.controller.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by sungang on 2016/8/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-common-config.xml"})
public class CountryTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;


    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();//获取mockMvc实例
    }

    /**
     *
     */
    @Test
    public void testList() throws Exception{
        RequestBuilder request = null;
        // 1、get查一下user列表，应该为空
        request = get("demo/country/list/");

        mockMvc.perform(request).andExpect(status().isOk());



    }
}
