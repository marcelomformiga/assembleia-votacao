
package br.com.formiga.assembleia_votacao.rest.request;


import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Data
public class CriarSessaoRequest {
    
    @NotNull(message = "[Pauta] não pode ser nula!")
    @Transient
    private Long pautaId;
    
    @Transient
    private Long tempoEmMinutos;
    
}