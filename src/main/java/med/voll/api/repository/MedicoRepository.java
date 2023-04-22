package med.voll.api.repository;

import med.voll.api.DTO.DadosListaMedicos;
import med.voll.api.domain.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<DadosListaMedicos> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select m from Medico m where m.id=:medico and m.ativo = true
            """)
    Optional<Medico> findByAtivoTrue(@Param("medico") Long medico);
}
