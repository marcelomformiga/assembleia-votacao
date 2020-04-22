
package br.com.formiga.assembleia_votacao.service.business.dto;


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
public class SessaoVotantesDTO extends GenericDTO {
    
    @NotNull(message = "[Sessão] não pode ser nula!")
    @Transient
    private SessaoDTO sessaoDTO;
    
    @NotNull(message = "[Associado] não pode ser nulo!")
    @Transient
    private AssociadoDTO associadoDTO;
    
}