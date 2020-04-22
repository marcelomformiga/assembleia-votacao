
package br.com.formiga.assembleia_votacao.domain.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Entity
@Table(name = "sessao", schema = "assembleia_votacao")
@Data
public class Sessao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "pauta_id", referencedColumnName = "id")
    private Pauta pauta;
    
    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;
    
    @Column(name = "data_hora_fim", nullable = false)
    private LocalDateTime dataHoraFim;
    
    @Column(name = "total_votos_sim", nullable = false)
    private Integer totalVotosSim;
    
    @Column(name = "total_votos_nao", nullable = false)
    private Integer totalVotosNao;
    
    @ManyToMany
    @JoinTable(name = "sessao_votantes",
               joinColumns = @JoinColumn(name = "sessao_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "associado_id", referencedColumnName = "id"))
    private List<Associado> associadosVotantes;
    
}