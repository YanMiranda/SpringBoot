package med.vol.api.medico.record;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.endereco.DadosEndereco;
import med.vol.api.medico.enums.Especialidade;

public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @Valid
        @NotNull
        DadosEndereco endereco) {
}
