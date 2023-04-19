package med.voll.api.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Especialidade;
import med.voll.api.domain.Medico;
import med.voll.api.dadosCompartilhados.Endereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String cpf,
        String telefone,
        Especialidade especialidade,
        Endereco endereco) {

        public DadosAtualizacaoMedico(Medico medico) {
                this(medico.getId(), medico.getNome(), medico.getCpf(), medico.getTelefone(),medico.getEspecialidade(),medico.getEndereco());
        }
}
