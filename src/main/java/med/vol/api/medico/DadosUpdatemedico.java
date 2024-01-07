package med.vol.api.medico;

import jakarta.validation.constraints.NotNull;
import med.vol.api.endereco.DadosEndereco;

public record DadosUpdatemedico(
        @NotNull
        Long id,
        String telefone,
        String nome,
        DadosEndereco endereco) {
}