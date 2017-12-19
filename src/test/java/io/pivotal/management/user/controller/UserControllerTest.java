package io.pivotal.management.user.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.fail;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    UserController controller;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {}

    @Test
    public void serviceUpTest() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content().json("{'data':'success'}"));
    }

    @Test
    public void getUserTest() throws Exception {
        this.mockMvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createUserTest() throws Exception {
        this.mockMvc.perform(post("/user/john/doe/johndoe")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("johndoe")));
    }

    @Test
    public void updateUserTest() throws Exception {
        this.mockMvc.perform(put("/user")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest() throws Exception {
        this.mockMvc.perform(delete("/user")).andDo(print()).andExpect(status().isOk());
    }
}