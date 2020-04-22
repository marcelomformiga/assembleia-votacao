
package br.com.formiga.assembleia_votacao.domain.infrasctructure.repository;


import br.com.formiga.assembleia_votacao.domain.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author formiga
 */
@Repository(value = "sessaoRepository")
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    
}