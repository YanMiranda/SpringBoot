package med.vol.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.domain.medico.record.DadosDetalhamentoMedico;
import med.vol.api.domain.medico.record.DadosCadastroMedico;
import med.vol.api.domain.medico.record.DadosUpdatemedico;
import med.vol.api.domain.medico.record.DatosListagemMedico;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.interfaces.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> insert(@RequestBody @Valid DadosCadastroMedico data, UriComponentsBuilder uriBuilder) {
        Medico medico = new Medico(data);
        repository.save(medico);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListagemMedico>> list(@PageableDefault(size = 5, sort = {"nome"}) Pageable pagination) {
        return ResponseEntity.ok(repository.findAllByAtivoTrue(pagination).map(DatosListagemMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> update(@RequestBody @Valid DadosUpdatemedico data) {
        Medico medico = repository.getReferenceById(data.id());
        medico.atualizar(data);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Medico> detailing(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> delete(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}
