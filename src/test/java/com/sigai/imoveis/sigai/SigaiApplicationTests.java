package com.sigai.imoveis.sigai;

import com.sigai.imoveis.sigai.servicosimoveis.controller.FotoController;
import com.sigai.imoveis.sigai.servicosimoveis.controller.ImovelController;
import com.sigai.imoveis.sigai.servicosimoveis.dto.FotoRequestDTO;
import com.sigai.imoveis.sigai.servicosimoveis.dto.FotoResponseDTO;
import com.sigai.imoveis.sigai.servicosimoveis.dto.ImovelRequestDTO;
import com.sigai.imoveis.sigai.servicosimoveis.dto.ImovelResponseDTO;
import com.sigai.imoveis.sigai.servicosimoveis.exception.FotoNaoEncontradaException;
import com.sigai.imoveis.sigai.servicosimoveis.exception.ImovelNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SigaiApplicationTests {

    @Autowired
    private ImovelController controller;

    @Autowired
    private WebApplicationContext context;

    @Test
    void testCriarViaHttpValidaCampos() throws Exception {
        var mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        int quantidadeAntes = controller.listar().size();
        for (String json : List.of(
                """
                {"endereco":"   ","valorAluguel":1500}
                """,
                """
                {"endereco":"Rua Teste, 123","valorAluguel":0}
                """,
                """
                {"endereco":"Rua Teste, 123","valorAluguel":1500,"descricao":"%s"}
                """.formatted("a".repeat(501)))) {
            mockMvc.perform(post("/imoveis")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
        assertEquals(quantidadeAntes, controller.listar().size());

        mockMvc.perform(post("/imoveis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"endereco":"Rua Teste, 123","valorAluguel":1500,"descricao":"Apartamento"}
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

    @Test
    void testCriarEBuscarPorId() {
        ImovelRequestDTO novo = new ImovelRequestDTO("Rua Teste, 123", 1500.0, "Apartamento centro");
        ImovelResponseDTO criado = controller.criar(novo).getBody();
        assertNotNull(criado);
        assertNotNull(criado.getId());

        ImovelResponseDTO buscado = controller.buscarPorId(criado.getId());
        assertNotNull(buscado);
        assertEquals("Rua Teste, 123", buscado.getEndereco());

        List<ImovelResponseDTO> lista = controller.listar();
        assertFalse(lista.isEmpty());
    }

    @Test
    void testBuscarPorIdInexistenteLancaExcecao() {
        assertThrows(ImovelNaoEncontradoException.class, () -> controller.buscarPorId(99999L));
    }

    @Test
    void testBuscarPorIdInexistenteRetornaProblemDetail() throws Exception {
        var mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(get("/imoveis/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Imóvel não encontrado"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("Imóvel não encontrado com o ID: 99999"));
    }

    @Test
    void testValidacaoRetornaProblemDetail() throws Exception {
        var mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(post("/imoveis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"endereco":"   ","valorAluguel":1500}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Erro de validação"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("Erro de validação nos campos informados"))
                .andExpect(jsonPath("$.erros.endereco").exists());
    }
}
