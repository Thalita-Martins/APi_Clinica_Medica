package med.voll.api.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Medico;
import med.voll.api.endereco.Endereco;
import med.voll.api.enumTipos.Especialidade;

public record DadosAtualizacaoMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        Especialidade especialidade,
        @Valid Endereco endereco) {
        public DadosAtualizacaoMedico(Medico medico) {
                this(medico.getId(), medico.getNome(), medico.getTelefone(),medico.getEspecialidade(),medico.getEndereco());
        }
}
