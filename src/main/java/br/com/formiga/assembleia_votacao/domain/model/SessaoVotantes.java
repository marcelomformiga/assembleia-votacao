
package br.com.formiga.assembleia_votacao.domain.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;



/**
 *
 * @author formiga
 */
@Entity
@Table(name = "sessao_votantes", schema = "assembleia_votacao")
@Data
public class SessaoVotantes implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", referencedColumnName = "id", nullable = false)
    private Sessao sessao;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "associado_id", referencedColumnName = "id", nullable = false)
    private Associado associado;
    
}