package med.vol.api.domain.medico.record;

import med.vol.api.domain.endereco.Endereco;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.enums.Especialidade;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco, Boolean ativo) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco(), medico.getAtivo());
    }
}
