package med.voll.api.repository;

import med.voll.api.DTO.DadosListaPaciente;
import med.voll.api.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<DadosListaPaciente> findAllByAtivoTrue(Pageable pageable);
}
