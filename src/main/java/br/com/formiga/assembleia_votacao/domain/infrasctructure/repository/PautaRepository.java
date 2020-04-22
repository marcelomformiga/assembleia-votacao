
package br.com.formiga.assembleia_votacao.domain.infrasctructure.repository;


import br.com.formiga.assembleia_votacao.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author formiga
 */
@Repository(value = "pautaRepository")
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    
}