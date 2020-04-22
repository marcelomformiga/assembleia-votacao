
package br.com.formiga.assembleia_votacao.domain.infrasctructure.repository;


import br.com.formiga.assembleia_votacao.domain.model.Associado;
import br.com.formiga.assembleia_votacao.domain.model.Sessao;
import br.com.formiga.assembleia_votacao.domain.model.SessaoVotantes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author formiga
 */
@Repository(value = "sessaoVotantesRepository")
public interface SessaoVotantesRepository extends JpaRepository<SessaoVotantes, Long> {
    
    Optional<SessaoVotantes> findBySessaoAndAssociado(final Sessao sessao, final Associado associado);
}