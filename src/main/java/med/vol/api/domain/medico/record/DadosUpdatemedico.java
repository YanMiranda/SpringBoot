package med.vol.api.domain.medico.record;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.endereco.DadosEndereco;

public record DadosUpdatemedico(
        @NotNull
        Long id,
        String telefone,
        String nome,
        DadosEndereco endereco) {
}
