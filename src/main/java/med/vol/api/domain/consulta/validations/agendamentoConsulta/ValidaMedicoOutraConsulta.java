package med.vol.api.domain.consulta.validations.agendamentoConsulta;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.ConsultaRepository;
import med.vol.api.domain.consulta.records.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoOutraConsulta implements ValidadorAgendamentoConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validate(DadosAgendamentoConsulta dados) {
        boolean medicoComOutraConsulta = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoComOutraConsulta) {
            throw new ValidacaoException("Médico com outra consulta agendada");
        }
    }
}
