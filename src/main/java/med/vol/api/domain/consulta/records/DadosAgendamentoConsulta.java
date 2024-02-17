package med.vol.api.domain.consulta.records;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.medico.enums.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
