package med.vol.api.domain.consulta.validations.agendamentoConsulta;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.records.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidaHorarioAntecedencia implements ValidadorAgendamentoConsultas {

    public void validate(DadosAgendamentoConsulta dados) {
        LocalDateTime data = dados.data();
        LocalDateTime dataAtual = LocalDateTime.now();
        long diferencaEmHoras = Duration.between(dataAtual, data).toMinutes();

        if (diferencaEmHoras < 30) {
            throw new ValidacaoException("A consulta deve ser agendada com no mínimo 30 minutos de antecedência");
        }

    }
}
