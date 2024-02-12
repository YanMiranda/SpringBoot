package med.vol.api.domain.paciente.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank(message = "O Paciente deve ter um nome")
        String nome,
        @NotBlank(message = "O E-mail deve ser preenchido")
        @Email
        String email,

        @NotBlank(message = "O Telefone deve ser preenchido")
        String telefone,
        @NotBlank(message = "O CPF deve ser preenchido")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,

        @NotNull(message = "O Endereço deve ser preenchido")
        @Valid
        DadosEndereco endereco) {
}
