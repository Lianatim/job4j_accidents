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
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.jpa.AccidentServiceJpa;

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
class AccidentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentServiceJpa accidentService;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWhenCreate() throws Exception {
        this.mockMvc.perform(get("/accidents/formCreate"))
                .andDo(print())
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

    @Test
    @WithMockUser
    public void shouldRedirectWhenCreate() throws Exception {
        this.mockMvc.perform(post("/accidents/save")
                        .param("id", "1")
                        .param("name", "name")
                        .param("text", "text")
                        .param("address", "address")
                        .param("type.id", "1")
                        .param("rIds", "1")
                        .param("rIds", "2")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).add(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("name");
    }

    @Test
    @WithMockUser
    public void shouldRedirectWhenUpdate() throws Exception {
        this.mockMvc.perform(post("/accidents/update")
                        .param("id", "1")
                        .param("name", "name")
                        .param("text", "text")
                        .param("address", "address")
                        .param("type.id", "1")
                        .param("rIds", "1")
                        .param("rIds", "2")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).update(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("name");
    }
}