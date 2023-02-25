package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.jpa.UserServiceJpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RegControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceJpa userServiceJpa;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/reg"))
                .andExpect(content().string(
                        containsString("If you already have")));
    }

    @Test
    @WithMockUser
    public void shouldReturn404() throws Exception {
        this.mockMvc.perform(get("/something"))
                .andExpect(status().is(404));
    }

    @Test
    @WithMockUser
    public void shouldRedirectWhenSave() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("id", "1")
                        .param("password", "password")
                        .param("username", "name")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userServiceJpa).save(argument.capture());
        assertThat(argument.getValue().getUsername()).isEqualTo("name");
    }
}