package med.vol.api.domain.consulta.validations.agendamentoConsulta;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.records.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidaHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {

    public void validate(DadosAgendamentoConsulta dados) {
        LocalDateTime data = dados.data();
        boolean domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesAberturaClinica = data.getHour() < 7;
        boolean depoisFechamentoClinica = data.getHour() > 18;

        if (domingo || antesAberturaClinica || depoisFechamentoClinica) {
            throw new ValidacaoException("Horário de funcionamento da clínica: Segunda a Sábado, das 07:00 às 18:00");
        }
    }
}
