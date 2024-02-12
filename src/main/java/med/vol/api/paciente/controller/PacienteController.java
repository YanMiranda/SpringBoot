package med.vol.api.paciente.controller;

import jakarta.validation.Valid;
import med.vol.api.paciente.Paciente;
import med.vol.api.paciente.interfaces.PacienteRepository;
import med.vol.api.paciente.records.DadosAtualizacaoPaciente;
import med.vol.api.paciente.records.DadosCadastroPaciente;
import med.vol.api.paciente.records.DadosDetalhamentoPaciente;
import med.vol.api.paciente.records.DadosListagemPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> insert(@RequestBody @Valid DadosCadastroPaciente data, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(data);
        repository.save(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        return ResponseEntity.ok(repository.findAllByAtivoTrue(pagination).map(DadosListagemPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> update(@RequestBody @Valid DadosAtualizacaoPaciente data) {
        Paciente paciente = repository.getReferenceById(data.id());
        paciente.update(data);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Paciente> delete(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detailing(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

}
