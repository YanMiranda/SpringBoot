package med.vol.api.controller;

import med.vol.api.domain.consulta.Consulta;
import med.vol.api.domain.consulta.records.DadosAgendamentoConsulta;
import med.vol.api.domain.consulta.records.DadosDetalhamentoConsulta;
import med.vol.api.domain.medico.enums.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

//    @Autowired
//    private JacksonTester<DadosAgendamentoConsulta> agendaDeConsultas;
//
//    @Autowired
//    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamento;

    @Test
    @WithMockUser
    @DisplayName("Deveria retornar códico 400 quando informções inválidas forem passadas")
    void agendarConsulta() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


//    @Test
//    @DisplayName("Deveria devolver codigo http 200 quando informações estão validas")
//    @WithMockUser
//    void agendar_cenario2() throws Exception {
//        var data = LocalDateTime.now().plusHours(1);
//        var especialidade = Especialidade.CARDIOLOGIA;
//
//        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data);
//        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);
//
//        var response = mvc
//                .perform(
//                        post("/consultas")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(dadosAgendamentoConsultaJson.write(
//                                        new DadosAgendamentoConsulta(2l, 5l, data, especialidade)
//                                ).getJson())
//                )
//                .andReturn().getResponse);
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//
//        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
//                dadosDetalhamento
//        ).getJson();
//
//        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
//    }

}