package med.vol.api.medico.record;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.endereco.DadosEndereco;
import med.vol.api.medico.enums.Especialidade;

public record DadosCadastroMedico(
        @NotBlank(message = "O Nome deve ser preenchido")
        String nome,
        @Email
        @NotBlank(message = "O E-mail deve ser preenchido")
        String email,
        @NotBlank(message = "O Telefone deve ser preenchido")
        String telefone,
        @NotBlank(message = "O CRM deve ser preenchido")
        @Pattern(regexp = "\\d{6}")
        String crm,
        @NotNull(message = "O Profissional deve ter uma especialidade")
        Especialidade especialidade,
        @Valid
        @NotNull(message = "O Endereço deve ser preenchido")
        DadosEndereco endereco) {
}
