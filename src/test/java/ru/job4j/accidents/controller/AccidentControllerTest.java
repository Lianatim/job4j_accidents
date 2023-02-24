package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWhenCreate() throws Exception {
        this.mockMvc.perform(get("/accidents/formCreate"))
                .andExpect(status().isOk())
                .andExpect(view().name("/accidents/formCreate"))
                .andExpect(content().string(
                        containsString("Новый инцидент")));
    }

    @Test
    @WithMockUser
    public void shouldReturn404() throws Exception {
        this.mockMvc.perform(get("/something"))
                .andExpect(status().is(404));
    }

    @Test
    @WithMockUser
    public void shouldReturn500() throws Exception {
        this.mockMvc.perform(get("/error"))
                .andExpect(status().is(500));
    }
}