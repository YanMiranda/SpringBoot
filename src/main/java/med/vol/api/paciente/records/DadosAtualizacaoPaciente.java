package med.vol.api.paciente.records;

import jakarta.validation.constraints.NotNull;
import med.vol.api.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
