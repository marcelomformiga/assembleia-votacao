
package br.com.formiga.assembleia_votacao.service.business.dto;


import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author formiga
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class SessaoDTO extends GenericDTO {
    
    @NotNull(message = "[Pauta] não pode ser nula!")
    @Transient
    private PautaDTO pauta;
    
    @NotNull(message = "[Data-Hora Início] não pode ser nula!")
    @Transient
    private LocalDateTime dataHoraInicio;
    
    @NotNull(message = "[Data-Hora Fim] não pode ser nula!")
    @Transient
    private LocalDateTime dataHoraFim;
    
    @Transient
    private Integer totalVotosSim;
    
    @Transient
    private Integer totalVotosNao;
    
    @Transient
    private List<AssociadoDTO> associadosVotantes;
    
}