package com.mini.autorizador.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.autorizador.application.request.CreditCardRecord;
import com.mini.autorizador.domain.service.CreditCardService;
import com.mini.autorizador.infrastructure.configuration.BasicAuthSecurity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(BasicAuthSecurity.class)
@WebMvcTest(controllers = CreditCardControllerTest.class)
@ExtendWith(MockitoExtension.class)
public class CreditCardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CreditCardService creditCardService;

    @Autowired
    private ObjectMapper objectMapper;

    private CreditCardRecord creditCardRecord;

    @BeforeEach
    public void init(){
        creditCardRecord = new CreditCardRecord("1234567890", "1234", 500.00);
    }

    @Test
    public void createCreditCardSuccessTest() throws Exception {
        String username = "username";
        String password = "password";
        given(creditCardService.createCreditCard(ArgumentMatchers.any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/api/v1/cartoes")
                        .with(httpBasic(username,password))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(creditCardRecord)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
