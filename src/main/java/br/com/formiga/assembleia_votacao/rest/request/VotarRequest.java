
package br.com.formiga.assembleia_votacao.rest.request;


import br.com.formiga.assembleia_votacao.domain.enumeration.VotoEnum;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Data
public class VotarRequest {
    
    @NotNull(message = "[Sessão] não pode ser nulo!")
    @Transient
    private Long sessaoId;
    
    @NotNull(message = "[Associado] não pode ser nulo!")
    @Transient
    private Long associadoId;
    
    @NotNull(message = "[Voto] não pode ser nulo!")
    @Transient
    private VotoEnum voto;
    
}