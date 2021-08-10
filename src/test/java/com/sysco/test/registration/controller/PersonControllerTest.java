package com.sysco.test.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysco.test.registration.config.CustomAuthenticationProvider;
import com.sysco.test.registration.model.Person;
import com.sysco.test.registration.service.PersonService;
import com.sysco.test.registration.utils.MockPersonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CustomAuthenticationProvider customAuthenticationProvider;

    @BeforeTestExecution
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void createPersonTestSuccess() throws Exception{
        Person person = MockPersonUtils.createPerson();
       mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createPersonWithoutFirstNameTestFail() throws Exception {
        Person person = MockPersonUtils.createPerson();
        person.setFirstName(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createPersonWithoutMobileNumberTestFail() throws Exception {
        Person person = MockPersonUtils.createPerson();
        person.setMobileNumber(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createPersonWithoutEmailIdTestFail() throws Exception {
        Person person = MockPersonUtils.createPerson();
        person.setEmailId(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createPersonWithoutPasswordTestFail() throws Exception {
        Person person = MockPersonUtils.createPerson();
        person.setPassword(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void getPersonByUsernameTestSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/harshu@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void getPersonByUsernameTestFail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser
    public void deletePersonByUsernameTestSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/deletePersonByUsername/harshu@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void deletePersonByUsernameTestFail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/deletePersonByUsername"))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }


}
