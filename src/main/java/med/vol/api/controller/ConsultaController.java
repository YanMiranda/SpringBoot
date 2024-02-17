package med.vol.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.domain.consulta.AgendaDeConsulta;
import med.vol.api.domain.consulta.records.DadosAgendamentoConsulta;
import med.vol.api.domain.consulta.records.DadosCancelamentoConsulta;
import med.vol.api.domain.consulta.records.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsulta service;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta data) {
        DadosDetalhamentoConsulta dados = service.agendar(data);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta data) {
        service.cancelar(data);
        return ResponseEntity.ok().build();
    }
}
