package med.voll.api.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Paciente;
import med.voll.api.endereco.Endereco;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        String cpf,
        @Valid Endereco endereco) {

        public DadosAtualizacaoPaciente(Paciente paciente){
               this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
        }
}
