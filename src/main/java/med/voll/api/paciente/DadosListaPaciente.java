package med.voll.api.paciente;

public record DadosListaPaciente(String nome, String email, String cpf) {

    public DadosListaPaciente(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
