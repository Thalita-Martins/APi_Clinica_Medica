package med.voll.api.repository;

import med.voll.api.DTO.DadosListaMedicos;
import med.voll.api.domain.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<DadosListaMedicos> findAllByAtivoTrue(Pageable pageable);

}
