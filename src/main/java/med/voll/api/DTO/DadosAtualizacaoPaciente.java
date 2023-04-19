package med.voll.api.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Paciente;
import med.voll.api.dadosCompartilhados.Endereco;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        String cpf,
        Endereco endereco) {

        public DadosAtualizacaoPaciente(Paciente paciente){
               this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
        }
}
