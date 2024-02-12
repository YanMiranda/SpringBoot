package med.vol.api.domain.medico.record;

import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.enums.Especialidade;

public record DatosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DatosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
