package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import med.voll.api.enumTipos.StatusAgendamento;

import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "hora_inicial")
    private Time horaInicial;

    @Column(name = "hora_final")
    private Time horaFinal;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    public Agendamento() {
        this.status = StatusAgendamento.AGENDADO;
    }

    public void mergeForUpdate(Agendamento agendamento){
        this.data = agendamento.getData();
        this.horaInicial = agendamento.getHoraInicial();
        this.horaFinal = agendamento.getHoraFinal();
        this.medico = agendamento.getMedico();
        this.paciente = agendamento.getPaciente();
        this.status = agendamento.getStatus();
    }
}
