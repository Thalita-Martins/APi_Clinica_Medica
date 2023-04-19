package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dadosCompartilhados.Endereco;
import med.voll.api.DTO.DadosAtualizacaoPaciente;
import med.voll.api.DTO.DadosCadastroPaciente;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "pacientes")
@Entity(name = "Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;
    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = dados.endereco();
    }

    public void mergeUpdate(DadosAtualizacaoPaciente dados){
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco =  dados.endereco();
    }
}
