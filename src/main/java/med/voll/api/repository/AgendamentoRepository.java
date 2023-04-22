package med.voll.api.repository;

import med.voll.api.domain.Agendamento;
import med.voll.api.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long>, JpaSpecificationExecutor<Agendamento> {
    List<Agendamento> findByMedico(Medico medico);
}
