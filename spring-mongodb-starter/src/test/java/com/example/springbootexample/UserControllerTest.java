package com.example.springbootexample;

import com.example.springbootexample.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    private static AppUser appUser;

    @WithMockUser
    @Test
    public void test1UserCreation() throws Exception {
       String content = "{\n" +
               "\t\"username\":\"user\",\n" +
               "    \"password\":\"user@123\",\n" +
               "    \"roles\":[\n" +
               "    \t\"USER\"]\n" +
               "}";

        MvcResult result = mvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username",is("user")))
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        appUser = new ObjectMapper().readValue(response.getString("data"),AppUser.class);
    }

    @WithMockUser
    @Test
    public void test2GetUserById() throws Exception {
        String id = appUser.getId();
        mvc.perform(get("/user/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username",is("user")));
    }

    @WithMockUser
    @Test
    public void test3UpdateUserById() throws Exception {
        String id = appUser.getId();
        appUser.setPassword("Pass@123");
        String content = new ObjectMapper().writeValueAsString(appUser);
        mvc.perform(put("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk());

    }

    @WithMockUser
    @Test
    public void test4DeleteUserById() throws Exception {
        String id = appUser.getId();
        mvc.perform(delete("/user/" + id))
                .andExpect(status().isOk());

    }
}
