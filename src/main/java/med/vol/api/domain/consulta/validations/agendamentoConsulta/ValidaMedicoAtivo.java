package med.vol.api.domain.consulta.validations.agendamentoConsulta;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.records.DadosAgendamentoConsulta;
import med.vol.api.domain.medico.interfaces.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoAtivo implements ValidadorAgendamentoConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validate(DadosAgendamentoConsulta dados) {
        Boolean medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (medicoAtivo == null || !medicoAtivo) {
            throw new ValidacaoException("Médico inativo");
        }

    }
}
