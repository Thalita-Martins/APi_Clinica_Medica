package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.Endereco;
import med.voll.api.DTO.DadosAtualizacaoMedico;
import med.voll.api.DTO.DadosCadastroMedico;
import med.voll.api.enumTipos.Especialidade;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
@Entity(name = "Medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = dados.endereco();
    }

    public void mergeUpdate(DadosAtualizacaoMedico dados){
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.endereco = dados.endereco();
        this.especialidade = dados.especialidade();
    }

    public void excluir() {
        this.ativo = false;
    }
}
