package br.com.gadoboi.api;

import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.CadastroRequest;
import br.com.gadoboi.api.infrastructure.adapter.in.rest.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GadoboiIntegrationTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper objectMapper;

    @Test
    void cadastroELoginDevemFuncionar() throws Exception {
        CadastroRequest cadastro = new CadastroRequest(
                "João", "Silva", "joao@test.com", "123.456.789-00",
                null, null, null, "M", null, "senha123", null);

        mvc.perform(post("/api/v1/auth/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastro)))
                .andExpect(status().isCreated());

        LoginRequest login = new LoginRequest("joao@test.com", "senha123");
        mvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.tipo").value("Bearer"));
    }

    @Test
    void endpointProtegidoSemTokenDeveRetornar403() throws Exception {
        mvc.perform(get("/api/v1/gado"))
                .andExpect(status().isForbidden());
    }

    @Test
    void cadastrarECriarGadoDeveRetornar201() throws Exception {
        // cadastro
        CadastroRequest cadastro = new CadastroRequest(
                "Maria", "Santos", "maria@test.com", "987.654.321-00",
                null, null, null, "F", null, "senha456", null);
        mvc.perform(post("/api/v1/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastro)))
                .andExpect(status().isCreated());

        // login
        MvcResult loginResult = mvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginRequest("maria@test.com", "senha456"))))
                .andExpect(status().isOk())
                .andReturn();

        String token = objectMapper.readTree(loginResult.getResponse().getContentAsString()).get("token").asText();

        // criar gado
        mvc.perform(post("/api/v1/gado")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nomeGado\": \"Nelore\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeGado").value("Nelore"));
    }
}
