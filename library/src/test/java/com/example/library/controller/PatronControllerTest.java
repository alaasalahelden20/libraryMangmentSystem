package com.example.library.controller;

import com.example.library.controller.PatronController;
import com.example.library.model.Patron;
import com.example.library.service.PatronService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatronController.class)
public class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService PatronService;

    @Test
    public void testGetPatronById() throws Exception {
        Patron patron = new Patron();
        patron.setName("patronname");
        patron.setContactInformation("contactInformation");

        when(PatronService.getPatronById(1L)).thenReturn(patron);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/patrons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("patronname"))
                .andExpect(jsonPath("$.contactInformation").value("contactInformation"));

    }
}
