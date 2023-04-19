package med.voll.api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.dadosCompartilhados.Endereco;

public record DadosCadastroPaciente(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String cpf,

        Endereco endereco
) {
}
