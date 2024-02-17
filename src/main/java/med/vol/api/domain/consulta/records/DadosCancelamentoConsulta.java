package med.vol.api.domain.consulta.records;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.consulta.MotivosCancelamentos;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivosCancelamentos motivo
) {
}
