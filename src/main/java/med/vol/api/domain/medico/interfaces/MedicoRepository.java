package med.vol.api.domain.medico.interfaces;

import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from Medicos m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
        and
                c.motivoCancelamento is null
            )
            order by rand()
            limit 1
""")
    Medico encontrePelaEspecialidade(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo
            FROM Medicos m
            WHERE m.id = :id
            """)
    Boolean findAtivoById(Long id);

}
