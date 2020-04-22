
package br.com.formiga.assembleia_votacao.domain.infrasctructure.repository;


import br.com.formiga.assembleia_votacao.domain.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author formiga
 */
@Repository(value = "associadoRepository")
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    
}