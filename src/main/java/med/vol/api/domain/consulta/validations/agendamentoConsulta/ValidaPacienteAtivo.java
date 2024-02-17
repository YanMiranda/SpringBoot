package med.vol.api.domain.consulta.validations.agendamentoConsulta;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.records.DadosAgendamentoConsulta;
import med.vol.api.domain.paciente.interfaces.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteAtivo implements ValidadorAgendamentoConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validate(DadosAgendamentoConsulta dados) {
        if (dados.idPaciente() == null) {
            throw new ValidacaoException("O paciente deve ser informado");
        }

        Boolean pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (pacienteAtivo == null || !pacienteAtivo) {
            throw new ValidacaoException("Paciente inativo");
        }
    }

}
