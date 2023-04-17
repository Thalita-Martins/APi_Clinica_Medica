package med.voll.api.DTO;

import med.voll.api.domain.Medico;
import med.voll.api.enumTipos.Especialidade;

public record DadosListaMedicos(

        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public DadosListaMedicos(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
