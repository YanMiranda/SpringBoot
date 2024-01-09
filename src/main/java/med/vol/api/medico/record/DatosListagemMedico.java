package med.vol.api.medico.record;

import med.vol.api.medico.enums.Especialidade;
import med.vol.api.medico.Medico;

public record DatosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DatosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
